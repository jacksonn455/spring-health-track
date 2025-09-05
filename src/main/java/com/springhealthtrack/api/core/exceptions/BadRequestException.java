package com.springhealthtrack.api.core.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataError::new).toList());
    }

    public record DataError(String field, String message) {
        public DataError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}