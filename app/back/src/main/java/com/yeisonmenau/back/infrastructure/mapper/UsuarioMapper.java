package com.yeisonmenau.back.infrastructure.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.persistence.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {
    private final ObjectMapper objectMapper;

    public Usuario requestToDomain(UsuarioRequest usuarioRequest) {
        return objectMapper.convertValue(usuarioRequest, Usuario.class);
    }

    public UsuarioResponse domainToResponse (Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getCorreo(),
                Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears()
        );
    }
    public UsuarioEntity domainToEntity(Usuario usuario) {
        return objectMapper.convertValue(usuario, UsuarioEntity.class);
    }
    public Usuario entityToDomain(UsuarioEntity usuarioEntity) {
        return objectMapper.convertValue(usuarioEntity, Usuario.class);
    }

}
