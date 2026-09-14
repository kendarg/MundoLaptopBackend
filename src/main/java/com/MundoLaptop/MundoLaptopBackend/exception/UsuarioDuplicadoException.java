package com.MundoLaptop.MundoLaptopBackend.exception;

public class UsuarioDuplicadoException extends RuntimeException {

    public UsuarioDuplicadoException(String mensaje) {
        super(mensaje);
    }
}