package com.yeisonmenau.back.application.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manejarValid(MethodArgumentNotValidException e) {
        Map<String, String> errores = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
        return errores;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> manejarIllegalArgument(IllegalArgumentException e) {
        return Map.of("error", e.getMessage());
    }

}

