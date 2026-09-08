package com.MundoLaptop.MundoLaptopBackend.controller;

import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.UsuarioDTO.UsuarioResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() { return usuarioService.listarUsuarios();}

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@RequestBody UsuarioRequestDTO dto){

        UsuarioResponseDTO creado = usuarioService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.actualizarUsuario(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return usuarioService.eliminarUsuario(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}




