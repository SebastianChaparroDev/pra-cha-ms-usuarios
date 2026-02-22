package com.pragma.challenge.pra_cha_ms_plazoleta.domain.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
