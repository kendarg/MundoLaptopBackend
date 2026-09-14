package com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}