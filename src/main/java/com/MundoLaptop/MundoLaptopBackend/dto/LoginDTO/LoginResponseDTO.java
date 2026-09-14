package com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO;

import com.MundoLaptop.MundoLaptopBackend.model.RolUsuario;

public record LoginResponseDTO(
        String token,
        String username,
        RolUsuario rol
) {
}