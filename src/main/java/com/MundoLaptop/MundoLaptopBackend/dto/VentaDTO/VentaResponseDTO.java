package com.MundoLaptop.MundoLaptopBackend.dto.VentaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDTO {

    private Long id;
    private LocalDateTime fechaVenta;
    private Double total;
    private Long clienteId;
    private String nombreCliente;
    private List<DetalleVentaResponseDTO> detalles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetalleVentaResponseDTO {
        private Long id;
        private Long productoId;
        private String nombreProducto;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;
    }
}