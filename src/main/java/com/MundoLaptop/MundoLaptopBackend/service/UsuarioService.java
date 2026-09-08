package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import com.MundoLaptop.MundoLaptopBackend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO datos) {
        Usuario usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());
        usuario.setPassword(datos.password());
        usuario.setTelefono(datos.telefono());
        Usuario creado = usuarioRepository.save(usuario);
        return mapearAUsuarioResponseDTO(creado);
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
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }

    private UsuarioResponseDTO mapearAUsuarioResponseDTO(Usuario usuario) {
        List<OrdenDeMatenimientoResponseDTO> Orden_mantenimiento = usuario.getOrdenesDeMantenimiento()
                .stream()
                .map(orden -> new OrdenDeMatenimientoResponseDTO(
                        orden.getId(),
                        orden.getDescripcion(),
                        orden.getFecha(),
                        orden.getUsuario().getId()
                ))
                .toList();
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono(),
                Orden_mantenimiento
        );
    }
}

