package com.yeisonmenau.back.application.port.in;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;

public interface CrearUsuarioUseCase {
    UsuarioResponse crearUsuario(UsuarioRequest usuario);
}
