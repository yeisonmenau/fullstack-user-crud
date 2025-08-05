package com.yeisonmenau.back.application.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manejarValid(MethodArgumentNotValidException e) {
        Map<String, String> respuesta = new LinkedHashMap<>();

        String primerError = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Error de validación");

        respuesta.put("error", primerError);
        return respuesta;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> manejarIllegalArgument(IllegalArgumentException e) {
        return Map.of("error", e.getMessage());
    }

}

