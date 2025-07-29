package com.yeisonmenau.back.infrastructure.persistence.adapter;

import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import com.yeisonmenau.back.infrastructure.persistence.entity.UsuarioEntity;
import com.yeisonmenau.back.infrastructure.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class UsuarioAdapter implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioMapper.domainToEntity(usuario);
        UsuarioEntity usuarioGuardado = usuarioJpaRepository.save(usuarioEntity);
        return usuarioMapper.entityToDomain(usuarioGuardado);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return List.of();
    }

    @Override
    public Usuario actualizarUsuario(Long cedula, Usuario usuario) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long cedula) {

    }
}
