package com.yeisonmenau.back.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long cedula;
    private String nombre;
    private String correo;
    private LocalDate fechaNacimiento;
}
