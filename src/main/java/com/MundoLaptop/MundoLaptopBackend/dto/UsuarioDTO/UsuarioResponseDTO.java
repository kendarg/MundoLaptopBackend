package com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO;

import com.MundoLaptop.MundoLaptopBackend.model.OrdenDeMantenimiento;
import com.MundoLaptop.MundoLaptopBackend.model.RolUsuario;

import java.util.List;

public record UsuarioResponseDTO<OrdenMantenimientoResponseDTO>(
        Long id,
        String nombre,
        RolUsuario rol,
        List<OrdenMantenimientoResponseDTO> ordenesDeMantenimiento
) {
}

