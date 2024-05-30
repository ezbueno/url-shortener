package com.buenoezandro.urlshortener.controller;

import com.buenoezandro.urlshortener.entity.exception.UrlNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UrlExceptionHandler {
    @ExceptionHandler(value = UrlNotFoundException.class)
    public ResponseEntity<Void> handleUrlNotFoundException(UrlNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}