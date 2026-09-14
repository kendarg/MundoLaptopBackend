package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO.OrdenDeMantenimientoResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import com.MundoLaptop.MundoLaptopBackend.repository.UsuarioRepository;


import jakarta.validation.constraints.Email;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO datos) {
        if (usuarioRepository.findByEmail(datos.email()).isPresent()) {
            throw new EmailDuplicadoException("Ya existe una cuenta con ese Email " + datos.email());
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());
        usuario.setPassword(passwordEncoder.encode(datos.password()));
        usuario.setTelefono(datos.telefono());
        Usuario creado = usuarioRepository.save(usuario);
        usuario.setRol(datos.rol());
        return new UsuarioResponseDTO(creado.getId(), creado.getNombre(), creado.getRol());
    }


    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapearAUsuarioResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioResponseDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapearAUsuarioResponseDTO);
    }



    @Transactional
    public Optional<UsuarioResponseDTO> actualizarUsuario(Long id, UsuarioRequestDTO datos) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(datos.nombre());
                    usuario.setEmail(datos.email());
                    usuario.setPassword(datos.password());
                    usuario.setTelefono(datos.telefono());
                    Usuario actualizado = usuarioRepository.save(usuario);
                    return mapearAUsuarioResponseDTO(actualizado);
                });
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UsuarioResponseDTO mapearAUsuarioResponseDTO(Usuario usuario) {
        List<OrdenDeMantenimientoResponseDTO> Orden_mantenimiento = usuario.getOrdenesDeMantenimiento()
                .stream()
                .map(orden -> new OrdenDeMantenimientoResponseDTO(
                        orden.getId(),
                        orden.getEstadoOrden(),
                        orden.getDiagnosticoNotas(),
                        orden.getUsuario().getId(),
                        orden.getUsuario().getNombre(),
                        orden.getServicio().getId(),
                        orden.getServicio().getNombre()
                ))
                .toList();
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getRol(),
                Orden_mantenimiento

        );
    }
}

