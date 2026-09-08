package com.MundoLaptop.MundoLaptopBackend.controller;

import com.MundoLaptop.MundoLaptopBackend.dto.FacturaDTO.FacturaRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.FacturaDTO.FacturaResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) { this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaResponseDTO> listar() { return facturaService.listarFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> buscarPorId(@PathVariable Long id) {
        return facturaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> crear(@RequestBody FacturaRequestDTO dto) {
        FacturaResponseDTO creado = facturaService.crearFactura(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> actualizar(@PathVariable Long id, @RequestBody FacturaRequestDTO dto) {
        return facturaService.actualizarFactura(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return facturaService.eliminarFactura(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}


