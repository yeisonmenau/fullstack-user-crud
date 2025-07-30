package com.yeisonmenau.back.application.service;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.application.port.in.ActualizarUsuarioUseCase;
import com.yeisonmenau.back.application.port.in.CrearUsuarioUseCase;
import com.yeisonmenau.back.application.port.in.EliminarUsuarioUseCase;
import com.yeisonmenau.back.application.port.in.MostrarUsuariosUseCase;
import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UsuarioService implements CrearUsuarioUseCase, MostrarUsuariosUseCase, ActualizarUsuarioUseCase, EliminarUsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuarioDominio = usuarioMapper.requestToEntity(usuarioRequest);
        Usuario usuarioCreado = usuarioRepositoryPort.crearUsuario(usuarioDominio);
        return usuarioMapper.domainToResponse(usuarioCreado);
    }

    @Override
    public List<UsuarioResponse> mostrarUsuarios() {
        List<Usuario> usuariosDominio = usuarioRepositoryPort.obtenerUsuarios();
        return usuariosDominio.stream()
                .map(usuarioMapper::domainToResponse)
                .toList();
    }

    @Override
    public UsuarioResponse actualizarUsuario(Long cedula, UsuarioRequest usuarioRequest) {
        Usuario usuarioDominio = usuarioMapper.requestToEntity(usuarioRequest);
        Usuario usuarioActualizado = usuarioRepositoryPort.actualizarUsuario(cedula, usuarioDominio);
        return usuarioMapper.domainToResponse(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long cedula) {
        usuarioRepositoryPort.eliminarUsuario(cedula);
    }
}
