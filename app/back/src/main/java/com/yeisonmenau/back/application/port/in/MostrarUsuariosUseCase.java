package com.yeisonmenau.back.application.port.in;

import com.yeisonmenau.back.application.dto.UsuarioResponse;


import java.util.List;

public interface MostrarUsuariosUseCase {
    List<UsuarioResponse> mostrarUsuarios();
}
