package com.yeisonmenau.back.application.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<Map<String, Object>> errors = new ArrayList<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            Map<String, Object> errorDetail = new LinkedHashMap<>();
            errorDetail.put("field", error.getField());
            errorDetail.put("rejectedValue", error.getRejectedValue());
            errorDetail.put("message", error.getDefaultMessage());
            errors.add(errorDetail);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", "Error en la petición. Número de errores: " + errors.size());
        response.put("errors", errors);
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarIllegalArgument(IllegalArgumentException e, HttpServletRequest request) {

        Map<String, Object> errorDetail = new LinkedHashMap<>();
        errorDetail.put("field", "global");
        errorDetail.put("rejectedValue", null);
        errorDetail.put("message", e.getMessage());

        List<Map<String, Object>> errors = new ArrayList<>();
        errors.add(errorDetail);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", "Error en la petición. Número de errores: " + errors.size());
        response.put("errors", errors);
        response.put("path", request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

