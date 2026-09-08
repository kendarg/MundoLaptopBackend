package com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO;

public record UsuarioRequestDTO(
    String nombre,
    String email,
    String password,
    int telefono
) {
}
