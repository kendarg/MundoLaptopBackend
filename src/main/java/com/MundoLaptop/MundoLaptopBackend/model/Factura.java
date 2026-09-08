package com.MundoLaptop.MundoLaptopBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_factura", nullable = false, length = 50, unique = true)
    private String numeroFactura;

    @Column(name = "fecha_facturacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;

    @Column(name = "monto_subtotal", nullable = false)
    private double montosubtotal;

    @Column(nullable = false)
    private double impuestos;

    @Column(name = "monto_total", nullable = false)
    private double montototal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false, unique = true)
    private Venta venta;
}