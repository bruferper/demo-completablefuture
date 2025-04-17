package com.bfz.completablefuture.infrastructure.adapter.in.rest.controller;

import com.bfz.completablefuture.domain.exception.InsufficientStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> onIsufficientStockException(InsufficientStockException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
