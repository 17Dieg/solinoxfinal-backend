package com.tanque.agua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanque.agua.dto.PerfilDto;
import com.tanque.agua.service.perfil.PerfilService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/")
    public List<PerfilDto> buscarTodos() {
        return perfilService.buscarTodos();
    }

    @GetMapping("/{idPerfil}")
    public ResponseEntity<PerfilDto> buscarPorIdPerfil(
    		@PathVariable("idPerfil") Integer idPerfil) {
    	return new ResponseEntity<>(perfilService
    			.buscarPorId(idPerfil), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PerfilDto> registrar(
            @Valid @RequestBody PerfilDto perfilDto) {
        return new ResponseEntity<>(perfilService
                .registrar(perfilDto), HttpStatus.CREATED);
    }

    @PutMapping("/{idPerfil}")
    public ResponseEntity<PerfilDto> actualizar(
            @PathVariable @NotNull(message = "Cannot be null") Integer idPerfil,
            @Valid @RequestBody PerfilDto perfilDto) {
        PerfilDto perfil = perfilService.buscarPorId(idPerfil);
        if (perfil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        perfilDto.setId(idPerfil);
        return new ResponseEntity<>(perfilService
                .registrar(perfilDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/{idPerfil}")
    public ResponseEntity<Boolean> eliminar(
    		@PathVariable("idPerfil") Integer idPerfil) {
        if (perfilService.eliminar(idPerfil)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
