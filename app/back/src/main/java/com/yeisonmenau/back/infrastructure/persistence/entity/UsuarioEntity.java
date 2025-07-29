package com.yeisonmenau.back.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    private Long cedula;
    private String nombre;
    private String correo;
    private LocalDate fechaNacimiento;
}
