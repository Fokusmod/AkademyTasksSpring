package com.example.AkademyTasks.handler;

import com.example.AkademyTasks.exception.AccountBannedException;
import com.example.AkademyTasks.exception.BadCredentialsException;
import com.example.AkademyTasks.exception.NotFoundException;
import com.example.AkademyTasks.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorizeHandler(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badRequestHandler(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundHandler(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AccountBannedException.class)
    public ResponseEntity<String> notFoundHandler(AccountBannedException ex) {
        return ResponseEntity.status(HttpStatus.LOCKED).body(ex.getMessage());
    }

}
