package com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String email,
        String password,
        int telefono,
        List Orden_mantenimiento,
        List Ventas
) {
}

