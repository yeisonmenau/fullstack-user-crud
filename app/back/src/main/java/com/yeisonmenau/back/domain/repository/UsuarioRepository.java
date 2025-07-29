package com.yeisonmenau.back.domain.repository;

import com.yeisonmenau.back.domain.model.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioRepository {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    Usuario actualizarUsuario(Long cedula, Usuario usuario);
    Map<String, String> eliminarUsuario(Long cedula);
}
