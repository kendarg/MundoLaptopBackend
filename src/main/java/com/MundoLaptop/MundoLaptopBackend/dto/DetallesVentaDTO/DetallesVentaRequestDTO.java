package com.MundoLaptop.MundoLaptopBackend.dto.DetallesVentaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallesVentaRequestDTO {
    private Long ventaId;
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
}
