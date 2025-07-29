package com.yeisonmenau.back.infrastructure.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {
    private final ObjectMapper objectMapper;

    public Usuario requestToEntity(UsuarioRequest usuarioRequest) {
        return objectMapper.convertValue(usuarioRequest, Usuario.class);
    }
    public UsuarioResponse entityToResponse (Usuario usuario){
        return new UsuarioResponse(
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getCorreo(),
                Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears()
                );
    }

}
