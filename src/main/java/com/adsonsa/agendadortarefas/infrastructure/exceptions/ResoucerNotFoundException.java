package com.adsonsa.agendadortarefas.infrastructure.exceptions;

public class ResoucerNotFoundException extends RuntimeException{

    public ResoucerNotFoundException(String message) {
        super(message);
    }
    public ResoucerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
