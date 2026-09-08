package com.MundoLaptop.MundoLaptopBackend.repository;

import com.MundoLaptop.MundoLaptopBackend.model.OrdenDeMantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDeMantenimientoRepository extends JpaRepository<OrdenDeMantenimiento, Long> {

    List<OrdenDeMantenimiento> findByUsuarioId(Long usuarioId);

    List<OrdenDeMantenimiento> findByServicioId(Long servicioId);

    List<OrdenDeMantenimiento> findByEstadoOrden(String estadoOrden);
}