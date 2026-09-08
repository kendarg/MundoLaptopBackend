package com.MundoLaptop.MundoLaptopBackend.dto.FacturaDTO;

import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioResponseDTO;

import java.util.Date;
import java.util.List;

public record FacturaResponseDTO (
        Long id,
        String numeroFactura,
        Date fechaFacturacion,
        double montoSubtotal,
        double impuestos,
        double montoTotal,
        List<UsuarioResponseDTO> usuarios
){
}


