package com.MundoLaptop.MundoLaptopBackend.repository;

import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}


