package com.yeisonmenau.back.application.port.out;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.domain.model.Usuario;

import java.util.List;

public interface UsuarioRepositoryPort {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    Usuario actualizarUsuario(Long cedula, Usuario usuario);
    void eliminarUsuario(Long cedula);
    Usuario buscarUsuarioPorCedula(Long cedula);
}
