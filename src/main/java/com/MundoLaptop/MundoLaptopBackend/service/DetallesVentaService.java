package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.model.DetallesVenta;
import com.MundoLaptop.MundoLaptopBackend.repository.DetallesVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetallesVentaService {

    private final DetallesVentaRepository detallesVentaRepository;

    public DetallesVentaService(DetallesVentaRepository detallesVentaRepository) {
        this.detallesVentaRepository = detallesVentaRepository;
    }

    @Transactional(readOnly = true)
    public List<DetallesVenta> obtenerTodos() {
        return detallesVentaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DetallesVenta> obtenerPorId(Long id) {
        return detallesVentaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<DetallesVenta> obtenerPorVenta(Long ventaId) {
        return detallesVentaRepository.findByVentaId(ventaId);
    }

    @Transactional
    public DetallesVenta guardar(DetallesVenta detallesVenta) {
        return detallesVentaRepository.save(detallesVenta);
    }

    @Transactional
    public void eliminar(Long id) {
        detallesVentaRepository.deleteById(id);

    }
}


