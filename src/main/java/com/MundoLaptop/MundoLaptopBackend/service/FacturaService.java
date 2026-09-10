package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.FacturaDTO.FacturaRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.FacturaDTO.FacturaResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.VentaDTO.VentaResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.model.Factura;
import com.MundoLaptop.MundoLaptopBackend.repository.FacturasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    private final FacturasRepository facturasRepository;

    public FacturaService(FacturasRepository facturasRepository) { this.facturasRepository = facturasRepository;
    }

    @Transactional(readOnly = true)
    public List<FacturaResponseDTO> listarFacturas() {
        return facturasRepository.findAll().stream()
                .map(this::mapearAUsuarioResponseDTO)
                .toList();
    }

    public Optional<FacturaResponseDTO> buscarPorId(Long id) {
        return facturasRepository.findById(id).map(this::mapearAUsuarioResponseDTO);
    }

    @Transactional
    public FacturaResponseDTO crearFactura(FacturaRequestDTO dto) {
        Factura factura = new Factura();
        factura.setNumeroFactura(dto.numeroFactura());
        factura.setFechaFacturacion(dto.fechaFacturacion());
        factura.setMontoSubtotal(dto.montoSubtotal());
        factura.setImpuestos(dto.impuestos());
        factura.setMontoTotal(dto.montoTotal());

        Factura creado = facturasRepository.save(factura);
        return mapearAUsuarioResponseDTO(creado);
    }

    @Transactional
    public Optional<FacturaResponseDTO> actualizarFactura(Long id, FacturaRequestDTO dto) {
        return facturasRepository.findById(id)
                .map(factura -> {
                    factura.setNumeroFactura(dto.numeroFactura());
                    factura.setFechaFacturacion(dto.fechaFacturacion());
                    factura.setMontoSubtotal(dto.montoSubtotal());
                    factura.setImpuestos(dto.impuestos());
                    factura.setMontoTotal(dto.montoTotal());
                    Factura actualizado = facturasRepository.save(factura);
                    return mapearAUsuarioResponseDTO(actualizado);
                });
    }


    public boolean eliminarFactura(Long id) {
        if (facturasRepository.existsById(id)) {
            facturasRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private  FacturaResponseDTO mapearAUsuarioResponseDTO(Factura factura) {

        return  new FacturaResponseDTO(
                factura.getId(),
                factura.getNumeroFactura(),
                factura.getFechaFacturacion(),
                factura.getMontoSubtotal(),
                factura.getImpuestos(),
                factura.getMontoTotal()
        );
    }
}

