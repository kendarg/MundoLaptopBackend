package com.MundoLaptop.MundoLaptopBackend.repository;

import com.MundoLaptop.MundoLaptopBackend.model.DetallesVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesVentaRepository extends JpaRepository<DetallesVenta, Long> {

    List<DetallesVenta> findByVentaId(Long ventaId);
}


