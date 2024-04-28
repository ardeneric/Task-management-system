package com.banque.banquemisr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = String.format("Invalid request body format. %s", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("{\"error\": \"%s\"}", errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = String.format("%s", ex.getBody());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("{\"error\": \"%s\"}", errorMessage));
    }
}