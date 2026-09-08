package com.MundoLaptop.MundoLaptopBackend.dto.CategoriaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDTO(
        @NotNull(message = "El nombre no puede ser null.")
        @NotBlank(message = "El campo no puede ser blanco.")
        String nombre) {


}


