package com.yeisonmenau.back.infrastructure.persistence.adapter;

import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import com.yeisonmenau.back.infrastructure.persistence.entity.UsuarioEntity;
import com.yeisonmenau.back.infrastructure.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        List<UsuarioEntity> usuariosEntidades = usuarioJpaRepository.findAll();
        return usuariosEntidades
                .stream()
                .map(usuarioMapper::entityToDomain)
                .toList();
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        UsuarioEntity usuarioEntityEncontrado = usuarioJpaRepository.findByCedula(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario con cédula "+id+" no encontrado"));
        UsuarioEntity usuarioEntidad = usuarioMapper.domainToEntity(usuario);
        usuarioEntidad.setId(usuarioEntityEncontrado.getId());
        UsuarioEntity usuarioReemplazado = usuarioJpaRepository.save(usuarioEntidad);
        return usuarioMapper.entityToDomain(usuarioReemplazado);

    }

    @Override
    public void eliminarUsuario(Long cedula) {

    }
}
