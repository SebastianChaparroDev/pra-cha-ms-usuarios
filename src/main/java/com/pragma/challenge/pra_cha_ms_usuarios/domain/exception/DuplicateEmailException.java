package com.pragma.challenge.pra_cha_ms_usuarios.domain.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
