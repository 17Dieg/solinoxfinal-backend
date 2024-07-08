package com.tanque.agua.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanque.agua.dto.PerfilDto;
import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.dto.UsuarioLogin;
import com.tanque.agua.exception.RequestException;
import com.tanque.agua.service.usuario.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public List<UsuarioDto> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> buscarPorIdUsuario(
    		@PathVariable("idUsuario") Integer idUsuario) {
    	return new ResponseEntity<>(usuarioService
    			.buscarPorId(idUsuario), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioDto> registrar(
            @Valid @RequestBody UsuarioDto usuarioDto) {
        return new ResponseEntity<>(usuarioService
                .registrar(usuarioDto), HttpStatus.CREATED);
    }

    @PutMapping("/{correo}")
    public ResponseEntity<UsuarioDto> actualizar(@PathVariable String correo, @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto usuario = usuarioService.buscarPorEmail(correo);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(usuarioService
                .actualizarUsuario(usuarioDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Boolean> eliminar(
    		@PathVariable("idUsuario") Integer idUsuario) {
        if (usuarioService.eliminar(idUsuario)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLogin> login(
            @Valid @RequestBody UsuarioLogin usuarioDto) {
        UsuarioLogin usuario = usuarioService.login(usuarioDto);

        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        throw new RequestException(HttpStatus.BAD_REQUEST.value(),  "Hubo un error en el login");
    }

}
