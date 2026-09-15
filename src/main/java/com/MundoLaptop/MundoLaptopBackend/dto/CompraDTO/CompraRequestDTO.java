package com.MundoLaptop.MundoLaptopBackend.dto.CompraDTO;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CompraRequestDTO(
        @NotEmpty(message = "El cliente es obligatorio")
        String cliente,

        @NotEmpty(message = "El carrito no puede estar vacío")
        List<ItemCompraDTO> items
) {}

