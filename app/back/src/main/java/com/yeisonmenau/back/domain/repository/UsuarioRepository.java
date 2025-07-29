package com.yeisonmenau.back.domain.repository;

import com.yeisonmenau.back.domain.model.Usuario;

import java.util.HashMap;
import java.util.List;

public interface UsuarioRepository {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    Usuario actualizarUsuario(Long cedula, Usuario usuario);
    HashMap<String, String> eliminarUsuario(Long cedula);
}
