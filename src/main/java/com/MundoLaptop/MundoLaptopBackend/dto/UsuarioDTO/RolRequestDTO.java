package com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO;

import com.MundoLaptop.MundoLaptopBackend.model.RolUsuario;
import jakarta.validation.constraints.NotNull;

public record RolRequestDTO(
        @NotNull(message = "El rol es obligatorio")
        RolUsuario rol
) {
}