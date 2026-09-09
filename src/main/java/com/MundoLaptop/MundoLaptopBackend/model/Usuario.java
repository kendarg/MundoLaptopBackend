package com.MundoLaptop.MundoLaptopBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolUsuario rol = RolUsuario.NORMAL;

    @OneToMany(mappedBy = "usuario")
    private List<OrdenDeMantenimiento> ordenesDeMantenimiento = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas = new ArrayList<>();
}
