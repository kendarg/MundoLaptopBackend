package com.MundoLaptop.MundoLaptopBackend.controller;

import com.MundoLaptop.MundoLaptopBackend.dto.MarcaDTO.MarcaRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.MarcaDTO.MarcaResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listarMarcas() {
        return ResponseEntity.ok(marcaService.listarMarcas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> obtenerMarcaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.obtenerMarcaPorId(id));
    }

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> crearMarca(@Valid @RequestBody MarcaRequestDTO datos) {
        MarcaResponseDTO nuevaMarca = marcaService.crearMarca(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> actualizarMarca(
            @PathVariable Long id,
            @Valid @RequestBody MarcaRequestDTO datos) {
        return ResponseEntity.ok(marcaService.actualizarMarca(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }
}