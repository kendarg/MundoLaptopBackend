package com.MundoLaptop.MundoLaptopBackend.controller;


import com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO.LoginRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO.LoginResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
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
