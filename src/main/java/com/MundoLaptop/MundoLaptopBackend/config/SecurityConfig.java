package com.MundoLaptop.MundoLaptopBackend.config;

import com.MundoLaptop.MundoLaptopBackend.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Peticiones Preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                            ).permitAll()

                        // Autenticación y Registro
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        // Consultas públicas del catálogo
                        .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/marcas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/servicios/**").permitAll()

                        // Gestión administrativa de catálogo (Soporta ADMINISTRADOR en mayúsculas o minúsculas)
                        .requestMatchers(HttpMethod.POST, "/api/productos/**", "/api/categorias/**", "/api/marcas/**", "/api/servicios/**").hasAnyAuthority("ADMINISTRADOR", "ROLE_ADMINISTRADOR", "administrador", "ROLE_administrador")
                        .requestMatchers(HttpMethod.PUT, "/api/productos/**", "/api/categorias/**", "/api/marcas/**", "/api/servicios/**").hasAnyAuthority("ADMINISTRADOR", "ROLE_ADMINISTRADOR", "administrador", "ROLE_administrador")
                        .requestMatchers(HttpMethod.DELETE, "/api/productos/**", "/api/categorias/**", "/api/marcas/**", "/api/servicios/**").hasAnyAuthority("ADMINISTRADOR", "ROLE_ADMINISTRADOR", "administrador", "ROLE_administrador")

                        // Ventas, Facturación y Mantenimientos
                        .requestMatchers("/api/ventas/**", "/api/detalles-venta/**", "/api/facturas/**").hasAnyAuthority("ADMINISTRADOR", "ROLE_ADMINISTRADOR", "NORMAL", "ROLE_NORMAL", "administrador", "normal")
                        .requestMatchers("/api/ordenes-mantenimiento/**").hasAnyAuthority("ADMINISTRADOR", "ROLE_ADMINISTRADOR", "NORMAL", "ROLE_NORMAL", "administrador", "normal")

                        // Seguridad por defecto para el resto de rutas
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Token inválido, ausente o expirado\"}");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"No tienes permiso para realizar esta acción\"}");
                        })
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:5500",
                "http://127.0.0.1:5500",
                "http://localhost:5502",
                "http://127.0.0.1:5502",
                "http://localhost:3000",
                "http://127.0.0.1:8080"
        ));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}