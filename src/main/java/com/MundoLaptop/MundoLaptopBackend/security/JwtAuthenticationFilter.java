package com.MundoLaptop.MundoLaptopBackend.security;

import com.MundoLaptop.MundoLaptopBackend.model.Usuario;
import com.MundoLaptop.MundoLaptopBackend.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.regionMatches(true, 0, "Bearer ", 0, 7)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = header.substring(7).trim();
            if (token.length() >= 2 && token.startsWith("\"") && token.endsWith("\"")) {
                token = token.substring(1, token.length() - 1).trim();
            }
            if (token.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtService.extraerEmail(token);
            Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow();

            // 1. Convertimos el nombre del rol a Mayúsculas y eliminamos espacios
            String rolUpper = usuario.getRol().name().toUpperCase().trim();

            // 2. Asignamos tanto la autoridad directa como la versión con prefijo ROLE_
            List<SimpleGrantedAuthority> autoridades = List.of(
                    new SimpleGrantedAuthority(rolUpper),          // "ADMINISTRADOR"
                    new SimpleGrantedAuthority("ROLE_" + rolUpper) // "ROLE_ADMINISTRADOR"
            );

            // 3. Creamos el objeto de autenticación con el usuario y las autoridades
            var autenticacion = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, autoridades);
            SecurityContextHolder.getContext().setAuthentication(autenticacion);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}