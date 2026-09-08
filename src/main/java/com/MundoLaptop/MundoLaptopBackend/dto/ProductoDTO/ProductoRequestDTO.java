package com.MundoLaptop.MundoLaptopBackend.dto.ProductoDTO;

import com.MundoLaptop.MundoLaptopBackend.model.Categoria;
import com.MundoLaptop.MundoLaptopBackend.model.CondicionProducto;
import com.MundoLaptop.MundoLaptopBackend.model.Marca;
import jakarta.validation.constraints.*;

import java.util.Map;

public record ProductoRequestDTO(
        @NotEmpty(message = "El nombre puede ser null ni puede estar vacio.")
        @Size(min = 8, max = 50, message = "El nombre debe tener entre 8 y 50 caracteres")
        String nombre,

        @NotEmpty(message = "El nombre puede ser null ni puede estar vacio.")
        @Size(min = 10, max = 10, message = "El numero de serie debe tener entre 10 caracteres")

        String numeroSerie,
        @NotEmpty(message = "El precio no puede ser null ni puede estar vacio.")

        @NotNull(message = "Debe ingresar id de categoria.")
        Long categoriaId,
        @NotNull(message = "Debe ingresar id de la marca.")
        Long marcaId,
        @Positive(message = "El precio debe ser positivo")

        double precio,
        @Min(value = 0, message = "El stock no puede ser negativo, debe ser mayor o igual a 0")
        int stock,
        @NotNull(message = "La condición del producto es obligatoria.")
        CondicionProducto condicion,
        @NotNull(message = "Las especificaciones no pueden ser nulas.")
        @NotEmpty(message = "Debe enviar al menos una especificación técnica.")
        Map<String, Object> especificaciones


) {


}


