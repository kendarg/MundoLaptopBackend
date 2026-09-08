package com.MundoLaptop.MundoLaptopBackend.controller;

import com.MundoLaptop.MundoLaptopBackend.model.DetallesVenta;
import com.MundoLaptop.MundoLaptopBackend.service.DetallesVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles-venta")
@CrossOrigin(origins = "*")
public class DetallesVentaController {

    private final DetallesVentaService detallesVentaService;

    public DetallesVentaController(DetallesVentaService detallesVentaService) {
        this.detallesVentaService = detallesVentaService;
    }

    @GetMapping
    public ResponseEntity<List<DetallesVenta>> listarTodos() {
        return ResponseEntity.ok(detallesVentaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesVenta> obtenerPorId(@PathVariable Long id) {
        return detallesVentaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<DetallesVenta>> listarPorVenta(@PathVariable Long ventaId) {
        return ResponseEntity.ok(detallesVentaService.obtenerPorVenta(ventaId));
    }

    @PostMapping
    public ResponseEntity<DetallesVenta> crear(@RequestBody DetallesVenta detallesVenta) {
        DetallesVenta nuevoDetalle = detallesVentaService.guardar(detallesVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detallesVentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

