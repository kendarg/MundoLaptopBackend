package com.MundoLaptop.MundoLaptopBackend.dto.ServicioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioRequestDTO {
    private String nombre;
    private String descripcion;
    private Double precioBase;
}