package com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO;

import com.MundoLaptop.MundoLaptopBackend.model.RolUsuario;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        RolUsuario rol
) {
}

