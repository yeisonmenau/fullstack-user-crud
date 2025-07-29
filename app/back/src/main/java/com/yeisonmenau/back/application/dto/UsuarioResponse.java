package com.yeisonmenau.back.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponse {
    private Long cedula;
    private String nombre;
    private String correo;
    private Integer edad;
}
