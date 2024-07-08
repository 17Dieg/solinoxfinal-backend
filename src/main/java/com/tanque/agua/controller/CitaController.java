package com.tanque.agua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanque.agua.dto.CitaDto;
import com.tanque.agua.service.cita.CitaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/")
    public List<CitaDto> buscarTodos() {
        return citaService.buscarTodos();
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<CitaDto> buscarPorIdCita(
    		@PathVariable("idCita") Integer idCita) {
    	return new ResponseEntity<>(citaService
    			.buscarPorId(idCita), HttpStatus.OK);
    }


    @PostMapping("/existe-cita")
    public ResponseEntity<Boolean> existeCitaPorFechaYHora(
            @Valid @RequestBody CitaDto citaDto) {
        return new ResponseEntity<>(citaService
                .existeCitaPorFechaYHora(citaDto), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CitaDto> registrar(
            @Valid @RequestBody CitaDto citaDto) {
        return new ResponseEntity<>(citaService
                .registrar(citaDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCita}")
    public ResponseEntity<Boolean> eliminar(
    		@PathVariable("idCita") Integer idCita) {
        if (citaService.eliminar(idCita)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
