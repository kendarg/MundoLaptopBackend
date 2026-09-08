package com.MundoLaptop.MundoLaptopBackend.dto.VentaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDTO {

    private Long clienteId;
    private List<DetalleVentaRequestDTO> detalles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class DetalleVentaRequestDTO {
        private Long productoId;
        private Integer cantidad;
        private Double precioUnitario;
    }
}

