package com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO;

import com.MundoLaptop.MundoLaptopBackend.model.RolUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String nombre,
        @NotBlank(message = "El correo electrónico es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        String password,
        @NotNull(message = "El teléfono es obligatorio")
        String telefono,
        @NotNull(message = "El rol es obligatorio")
        RolUsuario rol
) {
}
