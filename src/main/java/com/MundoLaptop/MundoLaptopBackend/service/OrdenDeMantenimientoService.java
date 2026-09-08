package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO.OrdenDeMantenimientoRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO.OrdenDeMantenimientoResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.OrdenDeMantenimiento;
import com.MundoLaptop.MundoLaptopBackend.model.Servicio;
import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import com.MundoLaptop.MundoLaptopBackend.repository.OrdenDeMantenimientoRepository;
import com.MundoLaptop.MundoLaptopBackend.repository.ServicioRepository;
import com.MundoLaptop.MundoLaptopBackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenDeMantenimientoService {

    @Autowired
    private OrdenDeMantenimientoRepository ordenDeMantenimientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    private OrdenDeMantenimientoResponseDTO mapearAResponseDTO(OrdenDeMantenimiento orden) {
        OrdenDeMantenimientoResponseDTO dto = new OrdenDeMantenimientoResponseDTO();
        dto.setId(orden.getId());
        dto.setEstadoOrden(orden.getEstadoOrden());
        dto.setDiagnosticoNotas(orden.getDiagnosticoNotas());

        if (orden.getUsuario() != null) {
            dto.setClienteId(orden.getUsuario().getId());
        }

        if (orden.getServicio() != null) {
            dto.setServicioId(orden.getServicio().getId());
        }

        return dto;
    }

    public List<OrdenDeMantenimientoResponseDTO> obtenerTodas() {
        return ordenDeMantenimientoRepository.findAll()
                .stream()
                .map(this::mapearAResponseDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<OrdenDeMantenimientoResponseDTO> obtenerPorId(Long id) {
        return ordenDeMantenimientoRepository.findById(id)
                .map(this::mapearAResponseDTO);
    }

    public OrdenDeMantenimientoResponseDTO crearOrden(OrdenDeMantenimientoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Usuario/Cliente no encontrado con ID: " + dto.getClienteId()));

        Servicio servicio = servicioRepository.findById(dto.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + dto.getServicioId()));

        OrdenDeMantenimiento orden = new OrdenDeMantenimiento();
        orden.setEstadoOrden(dto.getEstadoOrden());
        orden.setDiagnosticoNotas(dto.getDiagnosticoNotas());
        orden.setUsuario(usuario);
        orden.setServicio(servicio);

        OrdenDeMantenimiento ordenGuardada = ordenDeMantenimientoRepository.save(orden);
        return mapearAResponseDTO(ordenGuardada);
    }

    public OrdenDeMantenimientoResponseDTO actualizarOrden(Long id, OrdenDeMantenimientoRequestDTO dto) {
        OrdenDeMantenimiento ordenExistente = ordenDeMantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de mantenimiento no encontrada con ID: " + id));

        if (dto.getClienteId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Usuario/Cliente no encontrado con ID: " + dto.getClienteId()));
            ordenExistente.setUsuario(usuario);
        }

        if (dto.getServicioId() != null) {
            Servicio servicio = servicioRepository.findById(dto.getServicioId())
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + dto.getServicioId()));
            ordenExistente.setServicio(servicio);
        }

        if (dto.getEstadoOrden() != null) {
            ordenExistente.setEstadoOrden(dto.getEstadoOrden());
        }

        if (dto.getDiagnosticoNotas() != null) {
            ordenExistente.setDiagnosticoNotas(dto.getDiagnosticoNotas());
        }

        OrdenDeMantenimiento ordenActualizada = ordenDeMantenimientoRepository.save(ordenExistente);
        return mapearAResponseDTO(ordenActualizada);
    }
    
    public void eliminarOrden(Long id) {
        if (!ordenDeMantenimientoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Orden no encontrada con ID: " + id);
        }
        ordenDeMantenimientoRepository.deleteById(id);
    }
} 