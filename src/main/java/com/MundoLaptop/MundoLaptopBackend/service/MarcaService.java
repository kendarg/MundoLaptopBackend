package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.MarcaDTO.MarcaRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.MarcaDTO.MarcaResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Marca;
import com.MundoLaptop.MundoLaptopBackend.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Transactional(readOnly = true)
    public List<MarcaResponseDTO> listarMarcas() {
        return marcaRepository.findAll()
                .stream()
                .map(this::mapearAMarcaResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MarcaResponseDTO obtenerMarcaPorId(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + id));
        return mapearAMarcaResponseDTO(marca);
    }

    @Transactional
    public MarcaResponseDTO crearMarca(MarcaRequestDTO datos) {
        Marca marca = new Marca();
        marca.setNombre(datos.nombre());

        Marca creada = marcaRepository.save(marca);
        return mapearAMarcaResponseDTO(creada);
    }

    @Transactional
    public MarcaResponseDTO actualizarMarca(Long id, MarcaRequestDTO datos) {
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + id));

        marcaExistente.setNombre(datos.nombre());

        Marca actualizada = marcaRepository.save(marcaExistente);
        return mapearAMarcaResponseDTO(actualizada);
    }

    @Transactional
    public void eliminarMarca(Long id) {
        if (!marcaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Marca no encontrada con el ID: " + id);
        }
        marcaRepository.deleteById(id);
    }

    private MarcaResponseDTO mapearAMarcaResponseDTO(Marca marca) {
        return new MarcaResponseDTO(
                marca.getId(),
                marca.getNombre()
        );
    }
}