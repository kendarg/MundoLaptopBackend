package com.MundoLaptop.MundoLaptopBackend.dto.ProductoDTO;

import com.MundoLaptop.MundoLaptopBackend.model.CondicionProducto;

import java.util.Map;

public record ProductoResponseDTO(

        Long id,
        String nombre,
        String numeroserie,
        Long categoriaId,
        Long marcaId,
        double precio,
        int stock,
        CondicionProducto condicion,
        Map<String, Object> especificaciones


) {


}


