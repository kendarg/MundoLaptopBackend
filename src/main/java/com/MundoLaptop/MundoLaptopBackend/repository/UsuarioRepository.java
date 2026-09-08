package com.MundoLaptop.MundoLaptopBackend.repository;

import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}


