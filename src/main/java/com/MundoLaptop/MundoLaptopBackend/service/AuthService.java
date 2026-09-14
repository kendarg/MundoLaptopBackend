package com.MundoLaptop.MundoLaptopBackend.service;

import com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO.LoginRequestDTO;
import com.MundoLaptop.MundoLaptopBackend.dto.LoginDTO.LoginResponseDTO;
import com.MundoLaptop.MundoLaptopBackend.exception.CredencialesInvalidasException;
import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import com.MundoLaptop.MundoLaptopBackend.repository.UsuarioRepository;
import com.MundoLaptop.MundoLaptopBackend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO iniciarSesion(LoginRequestDTO datos) {
        Usuario usuario = usuarioRepository.findByEmail(datos.email())
                .orElseThrow(() -> new CredencialesInvalidasException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(datos.password(), usuario.getPassword())) {
            throw new CredencialesInvalidasException("Usuario o contraseña incorrectos");
        }

        String token = jwtService.generarToken(usuario);
        return new LoginResponseDTO(token, usuario.getEmail(), usuario.getRol());
    }
}
