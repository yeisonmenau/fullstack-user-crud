package com.yeisonmenau.back.application.usecase;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CrearUsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponse ejecutar(UsuarioRequest usuarioRequest){
        Usuario usuarioEntidad = usuarioMapper.requestToEntity(usuarioRequest);
        Usuario usuarioCreado  = usuarioRepositoryPort.crearUsuario(usuarioEntidad);
        return usuarioMapper.entityToResponse(usuarioCreado);
    }
}
