package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.ProductoDTO.ProductoRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.ProductoDTO.ProductoResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Categoria;
import com.MundoLaptop.MundoLaptopBackend.model.Marca;
import com.MundoLaptop.MundoLaptopBackend.model.Producto;
import com.MundoLaptop.MundoLaptopBackend.repository.CategoriaRepository;
import com.MundoLaptop.MundoLaptopBackend.repository.MarcaRepository;
import com.MundoLaptop.MundoLaptopBackend.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository,
                           MarcaRepository marcaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapearAProductoResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        return mapearAProductoResponseDTO(producto);
    }

    @Transactional
    public ProductoResponseDTO crearProducto(ProductoRequestDTO datos) {
        Categoria categoria = categoriaRepository.findById(datos.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + datos.categoriaId()));

        Marca marca = marcaRepository.findById(datos.marcaId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + datos.marcaId()));

        Producto producto = new Producto();
        producto.setNombre(datos.nombre());
        producto.setNumeroSerie(datos.numeroSerie());
        producto.setCategoria(categoria);
        producto.setMarca(marca);
        producto.setPrecio(datos.precio());
        producto.setStock(datos.stock());
        producto.setCondicion(datos.condicion());
        producto.setEspecificaciones(datos.especificaciones());


        Producto creado = productoRepository.save(producto);
        return mapearAProductoResponseDTO(creado);
    }

    @Transactional
    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO datos) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        Categoria categoria = categoriaRepository.findById(datos.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + datos.categoriaId()));

        Marca marca = marcaRepository.findById(datos.marcaId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con el ID: " + datos.marcaId()));

        productoExistente.setNombre(datos.nombre());
        productoExistente.setNumeroSerie(datos.numeroSerie());
        productoExistente.setCategoria(categoria);
        productoExistente.setMarca(marca);
        productoExistente.setPrecio(datos.precio());
        productoExistente.setStock(datos.stock());
        productoExistente.setCondicion(datos.condicion());
        productoExistente.setEspecificaciones(datos.especificaciones());


        Producto actualizado = productoRepository.save(productoExistente);
        return mapearAProductoResponseDTO(actualizado);
    }

    @Transactional
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO mapearAProductoResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getNumeroSerie(),
                producto.getCategoria().getId(),
                producto.getMarca().getId(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCondicion(),
                producto.getEspecificaciones()
        );
    }
}