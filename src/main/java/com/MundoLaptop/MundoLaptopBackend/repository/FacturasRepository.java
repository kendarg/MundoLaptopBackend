package com.MundoLaptop.MundoLaptopBackend.repository;

import com.MundoLaptop.MundoLaptopBackend.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturasRepository extends JpaRepository<Factura, Long> {
}


