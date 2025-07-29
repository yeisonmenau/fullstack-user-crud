package com.yeisonmenau.back.application.dto;

import lombok.Data;

import java.time.LocalDate;
public class UsuarioRequest {
    private Long cedula;
    private String nombre;
    private String correo;
    private LocalDate fechaNacimiento;
}
