package com.yeisonmenau.back.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private Long cedula;
    private String nombre;
    private String correo;
    private LocalDate fechaNacimiento;
}
