package com.yeisonmenau.back.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long cedula;
    private String nombre;
    private String correo;
    private Integer edad;
}
