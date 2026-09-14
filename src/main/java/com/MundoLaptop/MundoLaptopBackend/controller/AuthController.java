package com.MundoLaptop.MundoLaptopBackend.controller;


import com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO.LoginRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO.LoginResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return authService.iniciarSesion(dto);
    }
}
