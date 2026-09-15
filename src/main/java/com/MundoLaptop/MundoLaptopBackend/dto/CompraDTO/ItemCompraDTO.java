package com.MundoLaptop.MundoLaptopBackend.dto.CompraDTO;

import jakarta.validation.constraints.NotNull;

public record ItemCompraDTO(
        @NotNull(message = "El ID del producto es obligatorio")
        Long id,

        @NotNull(message = "La cantidad es obligatoria")
        int cantidad
) {}