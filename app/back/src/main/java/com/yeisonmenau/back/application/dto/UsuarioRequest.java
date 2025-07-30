package com.yeisonmenau.back.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    @NotNull(message = "La cédula no puede ser nula")
    @Min(value = 1, message = "La cedula debe ser un número válido")
    private Long cedula;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @Email(message = "El correo debe ser una dirección de correo electrónico válida")
    private String correo;
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate fechaNacimiento;
}
