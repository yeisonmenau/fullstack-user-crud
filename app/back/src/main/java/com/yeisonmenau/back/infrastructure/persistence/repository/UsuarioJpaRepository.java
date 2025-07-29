package com.yeisonmenau.back.infrastructure.persistence.repository;

import com.yeisonmenau.back.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
}
