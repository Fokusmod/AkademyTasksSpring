package com.example.AkademyTasks.exception;

import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PropertyBindingException.class)
    public ResponseEntity<String> propertyBindHandler(PropertyBindingException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Json содержит некоректные свойства. Проверьте данные введенные вами.");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> notFoundEntity(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundEntity(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
