package com.yeisonmenau.back.application.service;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.application.port.in.CrearUsuarioUseCase;
import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrearUsuarioService implements CrearUsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest usuario) {
        Usuario usuarioDominio = usuarioMapper.requestToEntity(usuario);
        Usuario usuarioCreado = usuarioRepositoryPort.crearUsuario(usuarioDominio);
        return usuarioMapper.domainToResponse(usuarioCreado);
    }
}
