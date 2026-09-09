package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO.OrdenDeMantenimientoResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import com.MundoLaptop.MundoLaptopBackend.model.Venta;
import com.MundoLaptop.MundoLaptopBackend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono(),
                Orden_mantenimiento,
                usuario.getVentas()
        );
    }
}

