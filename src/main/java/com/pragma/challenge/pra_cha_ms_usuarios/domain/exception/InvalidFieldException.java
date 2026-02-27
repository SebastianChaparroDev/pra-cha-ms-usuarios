package com.pragma.challenge.pra_cha_ms_usuarios.domain.exception;

public class InvalidFieldException extends RuntimeException {
    public InvalidFieldException(String message){
        super(message);
    }
}
