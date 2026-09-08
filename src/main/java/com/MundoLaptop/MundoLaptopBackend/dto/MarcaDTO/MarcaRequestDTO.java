package com.MundoLaptop.MundoLaptopBackend.dto.MarcaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MarcaRequestDTO(

        @NotBlank(message = "Tiene que ingresa un nombre de Marca oblitgatorio.")
        @NotNull(message = "No puede ser null el campo.")
        String nombre

) {


}


