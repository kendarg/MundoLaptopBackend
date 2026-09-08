package com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeMantenimientoRequestDTO {

    private String estadoOrden;
    private String diagnosticoNotas;
    private Long clienteId;
    private Long servicioId;
}