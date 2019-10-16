package com.example.autonyilvantartowebrest;

import com.example.autonyilvantartologic.exceptions.CarIdMustBeUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CarIdMustBeUniqueException.class)
    public ResponseEntity<?> handleCarIdMustBeUniqueException(CarIdMustBeUniqueException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
