package org.example.bankingportal.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //making it restFul;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) throws RuntimeException {
        log.warn("{}, {}", request.getContextPath(), ex.getMessage());
        return new ResponseEntity<>(errorResponse(ex), HttpStatus.BAD_REQUEST);

    }

    private ErrorResponse errorResponse(Throwable ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldError = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldError, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
        log.warn("{}, {}", request.getContextPath(), ex.getMessage());
        return new ResponseEntity<>(errorResponse(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
