package com.yeisonmenau.back.exception;

import com.yeisonmenau.back.application.exception.ExcepcionesGlobales;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExepcionesGlobalesTest {
    @Mock
    MethodArgumentNotValidException e;

    @Mock
    BindingResult bindingResult;

    @Mock
    FieldError fieldError1;

    @Mock
    FieldError fieldError2;

    @InjectMocks
    ExcepcionesGlobales excepcionesGlobales;

    @Test
    public void testManejarValid() {
        List<FieldError> fieldErrors = Arrays.asList(fieldError1, fieldError2);

        // Configurar el primer error
        when(fieldError1.getField()).thenReturn("fechaNacimiento");
        when(fieldError1.getDefaultMessage()).thenReturn("La fecha de nacimiento debe ser una fecha pasada");

        // Configurar el segundo error
        when(fieldError2.getField()).thenReturn("correo");
        when(fieldError2.getDefaultMessage()).thenReturn("El correo debe ser una dirección de correo electrónico válida");

        // Configurar el BindingResult para devolver la lista de errores
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Configurar la excepción para devolver el BindingResult
        when(e.getBindingResult()).thenReturn(bindingResult);

        // Resultado esperado
        Map<String, String> erroresEsperados = new LinkedHashMap<>();
        erroresEsperados.put("fechaNacimiento", "La fecha de nacimiento debe ser una fecha pasada");
        erroresEsperados.put("correo", "El correo debe ser una dirección de correo electrónico válida");

        // Act - Ejecutar el método
        Map<String, String> resultado = excepcionesGlobales.manejarValid(e);

        // Assert - Verificar el resultado
        assertThat(resultado).isEqualTo(erroresEsperados);
        assertThat(resultado.size()).isEqualTo(2);
        assertThat(resultado.get("fechaNacimiento")).isEqualTo("La fecha de nacimiento debe ser una fecha pasada");
        assertThat(resultado.get("correo")).isEqualTo("El correo debe ser una dirección de correo electrónico válida");

        // Verificar que se llamaron los métodos correctos
        verify(e).getBindingResult();
        verify(bindingResult).getFieldErrors();
        verify(fieldError1).getField();
        verify(fieldError1).getDefaultMessage();
        verify(fieldError2).getField();
        verify(fieldError2).getDefaultMessage();
    }
    @Test
    public void testManejarIllegalArgument() {
        // Arrange - Preparar el escenario
        String mensajeError = "Usuario con cédula 123 no encontrado";
        IllegalArgumentException exception = new IllegalArgumentException(mensajeError);

        // Act - Ejecutar el método a probar
        Map<String, String> resultado = excepcionesGlobales.manejarIllegalArgument(exception);

        // Assert - Verificar el resultado
        assertThat(resultado.get("error")).isEqualTo(mensajeError);
        assertThat(resultado.size()).isEqualTo(1);
    }

}

