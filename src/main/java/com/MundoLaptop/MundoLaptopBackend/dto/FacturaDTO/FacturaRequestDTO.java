package com.MundoLaptop.MundoLaptopBackend.dto.FacturaDTO;

import com.MundoLaptop.MundoLaptopBackend.model.Venta;
import java.util.Date;

public record FacturaRequestDTO (
        String numeroFactura,
        Date fechaFacturacion,
        double montoSubtotal,
        double impuestos,
        double montoTotal,
        Venta venta
){
}


