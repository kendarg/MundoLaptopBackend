package com.MundoLaptop.MundoLaptopBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Si las excepciones están en un subpaquete diferente, asegúrate de importarlas aquí.
// Import de ejemplo: import com.MundoLaptop.MundoLaptopBackend.exception.custom.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> manejarNoEncontrado(ResourceNotFoundException ex) {
        return construirRespuestaError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<Map<String, String>> manejarCredencialesInvalidas(CredencialesInvalidasException ex) {
        return construirRespuestaError(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ResponseEntity<Map<String, String>> manejarUsuarioDuplicado(UsuarioDuplicadoException ex) {
        return construirRespuestaError(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    // Método auxiliar para evitar repetir código en respuestas simples de error
    private ResponseEntity<Map<String, String>> construirRespuestaError(HttpStatus status, String mensaje) {
        Map<String, String> cuerpo = new HashMap<>();
        cuerpo.put("error", mensaje);
        return ResponseEntity.status(status).body(cuerpo);
    }
}