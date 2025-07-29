package com.yeisonmenau.back.application.port.in;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.domain.model.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioUseCase {
    UsuarioResponse crearUsuario(UsuarioRequest usuario);
    List<UsuarioResponse> obtenerUsuarios();
    UsuarioResponse actualizarUsuario(Long cedula, UsuarioRequest usuario);
    void eliminarUsuario(Long cedula);
}
