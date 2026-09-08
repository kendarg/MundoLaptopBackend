package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.CategoriaDTO.CategoriaRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.CategoriaDTO.CategoriaResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Categoria;
import com.MundoLaptop.MundoLaptopBackend.repository.CategoriaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::mapearACategoriaResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO obtenerCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
        return mapearACategoriaResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO datos) {
        Categoria categoria = new Categoria();
        categoria.setNombre(datos.nombre());

        Categoria creada = categoriaRepository.save(categoria);
        return mapearACategoriaResponseDTO(creada);
    }

    @Transactional
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaRequestDTO datos) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));

        categoriaExistente.setNombre(datos.nombre());

        Categoria actualizada = categoriaRepository.save(categoriaExistente);
        return mapearACategoriaResponseDTO(actualizada);
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Categoría no encontrada con el ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaResponseDTO mapearACategoriaResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}