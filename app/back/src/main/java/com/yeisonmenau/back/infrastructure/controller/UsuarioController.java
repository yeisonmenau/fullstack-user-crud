package com.yeisonmenau.back.infrastructure.controller;

import com.yeisonmenau.back.application.dto.UsuarioRequest;
import com.yeisonmenau.back.application.dto.UsuarioResponse;
import com.yeisonmenau.back.application.port.in.ActualizarUsuarioUseCase;
import com.yeisonmenau.back.application.port.in.CrearUsuarioUseCase;
import com.yeisonmenau.back.application.port.in.EliminarUsuarioUseCase;
import com.yeisonmenau.back.application.port.in.MostrarUsuariosUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    private final CrearUsuarioUseCase crearUsuarioUseCase;
    private final MostrarUsuariosUseCase mostrarUsuariosUseCase;
    private final ActualizarUsuarioUseCase actualizarUsuarioUseCase;
    private final EliminarUsuarioUseCase eliminarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @Valid @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = crearUsuarioUseCase.crearUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> mostrarUsuarios() {
        List<UsuarioResponse> usuarios = mostrarUsuariosUseCase.mostrarUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    @PutMapping("/{cedula}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable Long cedula,
            @Valid @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = actualizarUsuarioUseCase.actualizarUsuario(cedula, usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);
    }
    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long cedula) {
        eliminarUsuarioUseCase.eliminarUsuario(cedula);
        return ResponseEntity.ok().build();
    }
}
