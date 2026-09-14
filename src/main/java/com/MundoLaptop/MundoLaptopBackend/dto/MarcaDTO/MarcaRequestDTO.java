package com.MundoLaptop.MundoLaptopBackend.dto.MarcaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MarcaRequestDTO(

        @NotNull(message = "No puede ser null el campo.")
        //@NotBlank(message = "Tiene que ingresa un nombre de Marca oblitgatorio.")
        String nombre

) {


}


