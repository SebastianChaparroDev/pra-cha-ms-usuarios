package com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.exceptionhandler;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.exception.DuplicateDocumentException;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.exception.DuplicateEmailException;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.exception.MinorUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerValidationErrors(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return buildResponse(HttpStatus.BAD_REQUEST, "Error de validación", errors);
    }

    @ExceptionHandler(MinorUserException.class)
    public ResponseEntity<Map<String, Object>> handleMinorUser(MinorUserException exception){
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), null);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmail(DuplicateEmailException exception){
        return buildResponse(HttpStatus.CONFLICT, exception.getMessage(), null);
    }

    @ExceptionHandler(DuplicateDocumentException.class)
    public ResponseEntity<Map<String, Object>> haldeDuplicateDocument(DuplicateDocumentException exception){
        return buildResponse(HttpStatus.CONFLICT, exception.getMessage(), null);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message, Object details) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("mensaje", message);
        if (details != null) {
            body.put("detalles", details);
        }
        return ResponseEntity.status(status).body(body);
    }
}
