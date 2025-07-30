package com.yeisonmenau.back.infrastructure.config;

import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.application.service.UsuarioService;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public UsuarioService crearUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort, UsuarioMapper usuarioMapper){
        return new UsuarioService(usuarioRepositoryPort, usuarioMapper);
    }
}
