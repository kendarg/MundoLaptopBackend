package com.MundoLaptop.MundoLaptopBackend.controller;

import com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO.OrdenDeMantenimientoRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO.OrdenDeMantenimientoResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.service.OrdenDeMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-mantenimiento")
@CrossOrigin(origins = "*")
public class OrdenDeMantenimientoController {

    @Autowired
    private OrdenDeMantenimientoService ordenDeMantenimientoService;

    @GetMapping
    public ResponseEntity<List<OrdenDeMantenimientoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(ordenDeMantenimientoService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDeMantenimientoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ordenDeMantenimientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody OrdenDeMantenimientoRequestDTO dto) {
        try {
            OrdenDeMantenimientoResponseDTO nuevaOrden = ordenDeMantenimientoService.crearOrden(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrden);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarOrden(@PathVariable Long id, @RequestBody OrdenDeMantenimientoRequestDTO dto) {
        try {
            OrdenDeMantenimientoResponseDTO ordenActualizada = ordenDeMantenimientoService.actualizarOrden(id, dto);
            return ResponseEntity.ok(ordenActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrden(@PathVariable Long id) {
        try {
            ordenDeMantenimientoService.eliminarOrden(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}