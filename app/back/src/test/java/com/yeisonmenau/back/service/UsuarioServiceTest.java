package com.yeisonmenau.back.service;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.application.port.out.UsuarioRepositoryPort;
import com.yeisonmenau.back.application.service.UsuarioService;
import com.yeisonmenau.back.domain.model.Usuario;
import com.yeisonmenau.back.infrastructure.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioRequest usuarioRequest;
    private Usuario usuarioDominio;
    private Usuario usuarioDominio2;
    private Usuario usuarioCreado;
    private UsuarioResponse usuarioResponse;
    private UsuarioResponse usuarioResponse2;

    @BeforeEach
    public void setUp() {
        usuarioRequest = new UsuarioRequest(
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002,10,17)
        );
        usuarioDominio = new Usuario(
                null,
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002,10,17)
        );
        usuarioDominio2 = new Usuario(
                null,
                12346L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002,10,17)
        );
        usuarioCreado = new Usuario(
                1L,
                12345L,
                "Yeison",
                "yeison@mail.com",
                LocalDate.of(2002,10,17)
        );
        usuarioResponse = new UsuarioResponse(
                1L,
                12345L,
                "Yeison",
                "yeison@mail.com",
                22
        );
        usuarioResponse2 = new UsuarioResponse(
                2L,
                12346L,
                "Andres",
                "andres@mail.com",
                23
        );
    }
    @Test
    void crearUsuarioExitosamente(){

        // Arrange (Preparar): Configurar el comportamiento de los mocks
        when(usuarioMapper.requestToDomain(usuarioRequest)).thenReturn(usuarioDominio);
        when(usuarioRepositoryPort.crearUsuario(usuarioDominio)).thenReturn(usuarioCreado);
        when(usuarioMapper.domainToResponse(usuarioCreado)).thenReturn(usuarioResponse);

        // Act (Ejecutar): Llamar al metodo que se está probando
        UsuarioResponse resultado = usuarioService.crearUsuario(usuarioRequest);

        // Assert (Verificar): Verificar el resultado esperado
        assertNotNull(resultado);
        assertEquals(usuarioResponse.getId(), resultado.getId());
        assertEquals(usuarioResponse.getCedula(), resultado.getCedula());
        assertEquals(usuarioResponse.getNombre(), resultado.getNombre());
        assertEquals(usuarioResponse.getCorreo(), resultado.getCorreo());
        assertEquals(usuarioResponse.getEdad(), resultado.getEdad());

        // Verify (Verificar interacciones con mocks): Aquí se puede agregar la lógica de prueba
        verify(usuarioMapper).requestToDomain(usuarioRequest);
        verify(usuarioRepositoryPort).crearUsuario(usuarioDominio);
        verify(usuarioMapper).domainToResponse(usuarioCreado);
    }
    @Test
    void mostrarUsuariosExitosamente(){

        List<Usuario> usuariosDominio = List.of(usuarioDominio, usuarioDominio2);
        List<UsuarioResponse> usuariosResponse = List.of(usuarioResponse, usuarioResponse2);

        when(usuarioRepositoryPort.obtenerUsuarios()).thenReturn(usuariosDominio);
        when(usuarioMapper.domainToResponse(usuarioDominio)).thenReturn(usuarioResponse);
        when(usuarioMapper.domainToResponse(usuarioDominio2)).thenReturn(usuarioResponse2);

        List<UsuarioResponse> resultado = usuarioService.mostrarUsuarios();

        assertNotNull(resultado);

        assertEquals(2, resultado.size());
        assertEquals(usuarioResponse.getId(), resultado.get(0).getId());
        assertEquals(usuarioResponse.getCedula(), resultado.get(0).getCedula());
        assertEquals(usuarioResponse.getNombre(), resultado.get(0).getNombre());
        assertEquals(usuarioResponse.getCorreo(), resultado.get(0).getCorreo());
        assertEquals(usuarioResponse.getEdad(), resultado.get(0).getEdad());

        assertEquals(usuarioResponse2.getId(), resultado.get(1).getId());
        assertEquals(usuarioResponse2.getCedula(), resultado.get(1).getCedula());
        assertEquals(usuarioResponse2.getNombre(), resultado.get(1).getNombre());
        assertEquals(usuarioResponse2.getCorreo(), resultado.get(1).getCorreo());
        assertEquals(usuarioResponse2.getEdad(), resultado.get(1).getEdad());

        verify(usuarioRepositoryPort).obtenerUsuarios();
        verify(usuarioMapper).domainToResponse(usuarioDominio);
        verify(usuarioMapper).domainToResponse(usuarioDominio2);
    }
    @Test
    void actualizarUsuarioExitosamente() {

        Long cedula = 12345L;

        when(usuarioMapper.requestToDomain(usuarioRequest)).thenReturn(usuarioDominio);
        when(usuarioRepositoryPort.actualizarUsuario(cedula, usuarioDominio)).thenReturn(usuarioCreado);
        when(usuarioMapper.domainToResponse(usuarioCreado)).thenReturn(usuarioResponse);

        UsuarioResponse resultado = usuarioService.actualizarUsuario(cedula, usuarioRequest);

        assertNotNull(resultado);
        assertEquals(usuarioResponse.getId(), resultado.getId());
        assertEquals(usuarioResponse.getCedula(), resultado.getCedula());
        assertEquals(usuarioResponse.getNombre(), resultado.getNombre());
        assertEquals(usuarioResponse.getCorreo(), resultado.getCorreo());
        assertEquals(usuarioResponse.getEdad(), resultado.getEdad());

        verify(usuarioMapper).requestToDomain(usuarioRequest);
        verify(usuarioRepositoryPort).actualizarUsuario(cedula, usuarioDominio);
        verify(usuarioMapper).domainToResponse(usuarioCreado);
    }
    @Test
    void eliminarUsuarioExitosamente() {
        Long cedula = 12345L;

        usuarioService.eliminarUsuario(cedula);

        verify(usuarioRepositoryPort).eliminarUsuario(cedula);
    }

}
