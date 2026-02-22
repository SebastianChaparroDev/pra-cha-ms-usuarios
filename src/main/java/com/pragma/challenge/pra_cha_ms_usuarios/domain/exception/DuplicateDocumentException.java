package com.pragma.challenge.pra_cha_ms_usuarios.domain.exception;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException(String message) {
        super(message);
    }
}
