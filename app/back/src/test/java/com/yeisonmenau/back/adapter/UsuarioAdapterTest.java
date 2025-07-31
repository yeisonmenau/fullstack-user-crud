package com.yeisonmenau.back.adapter;

import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import com.yeisonmenau.back.infrastructure.persistence.adapter.UsuarioAdapter;
import com.yeisonmenau.back.infrastructure.persistence.entity.UsuarioEntity;
import com.yeisonmenau.back.infrastructure.persistence.repository.UsuarioJpaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioAdapterTest {
    @Mock
    private UsuarioJpaRepository usuarioJpaRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioAdapter usuarioAdapter;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;
    private UsuarioEntity usuarioGuardado;
    private Usuario crearUsuario;
    private UsuarioEntity usuarioEntityActualizado;
    Long cedula;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario(
                1L,
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002, 10, 17)
        );
        usuarioEntity = new UsuarioEntity(
                null,
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002, 10, 17)
        );
        usuarioGuardado = new UsuarioEntity(
                1L,
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002, 10, 17)
        );
        crearUsuario = new Usuario(
                1L,
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002, 10, 17)
        );
        usuarioEntityActualizado = new UsuarioEntity(
                usuarioEntity.getId(),  // Este es el ID que se setea dentro del adapter
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getFechaNacimiento()
        );
        cedula = 1L;

    }

    @Test
    public void obtenerUsuariosTest() {

        when(usuarioMapper.domainToEntity(usuario)).thenReturn(usuarioEntity);
        when(usuarioJpaRepository.save(usuarioEntity)).thenReturn(usuarioGuardado);
        when(usuarioMapper.entityToDomain(usuarioGuardado)).thenReturn(crearUsuario);

        Usuario resultado = usuarioAdapter.crearUsuario(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioJpaRepository).save(usuarioEntity);
    }
    @Test
    void crearUsuarioTest() {

        when(usuarioMapper.domainToEntity(usuario)).thenReturn(usuarioEntity);
        when(usuarioJpaRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioMapper.entityToDomain(usuarioEntity)).thenReturn(usuario);

        Usuario resultado = usuarioAdapter.crearUsuario(usuario);

        assertEquals(usuario, resultado);
        verify(usuarioJpaRepository).save(usuarioEntity);
    }
    @Test
    void actualizarUsuarioTest() {
        when(usuarioJpaRepository.findByCedula(cedula)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.domainToEntity(usuario)).thenReturn(new UsuarioEntity(
                null,
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getFechaNacimiento()
        ));

        when(usuarioJpaRepository.save(usuarioEntityActualizado)).thenReturn(usuarioGuardado);
        when(usuarioMapper.entityToDomain(usuarioGuardado)).thenReturn(usuario);

        Usuario resultado = usuarioAdapter.actualizarUsuario(cedula, usuario);

        assertEquals(usuario, resultado);
        verify(usuarioJpaRepository).save(usuarioEntityActualizado);
    }

    @Test
    void eliminarUsuarioTest() {

        when(usuarioJpaRepository.findByCedula(cedula)).thenReturn(Optional.of(usuarioEntity));

        usuarioAdapter.eliminarUsuario(cedula);

        verify(usuarioJpaRepository).delete(usuarioEntity);
    }
}
