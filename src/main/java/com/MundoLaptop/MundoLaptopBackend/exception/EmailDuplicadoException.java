package com.MundoLaptop.MundoLaptopBackend.exception;

public class EmailDuplicadoException  extends RuntimeException{
    public EmailDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
