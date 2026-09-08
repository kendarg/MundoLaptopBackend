package com.MundoLaptop.MundoLaptopBackend.dto.OrdenDeMantenimientoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeMantenimientoResponseDTO {

    private Long id;
    private String estadoOrden;
    private String diagnosticoNotas;
    private Long clienteId;
    private String nombreCliente;
    private Long servicioId;
    private String nombreServicio;
}
