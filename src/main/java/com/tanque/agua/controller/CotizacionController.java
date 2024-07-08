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

import com.tanque.agua.dto.CotizacionDto;
import com.tanque.agua.service.cotizacion.CotizacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cotizaciones")
public class CotizacionController {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @GetMapping("/")
    public List<CotizacionDto> buscarTodos() {
        return cotizacionService.buscarTodos();
    }

    @GetMapping("/{idCotizacion}")
    public ResponseEntity<CotizacionDto> buscarPorIdCotizacion(
    		@PathVariable("idCotizacion") Integer idCotizacion) {
    	return new ResponseEntity<>(cotizacionService
    			.buscarPorId(idCotizacion), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CotizacionDto> registrar(
            @Valid @RequestBody CotizacionDto cotizacionDto) {

        return new ResponseEntity<>(cotizacionService
                .registrar(cotizacionDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCotizacion}")
    public ResponseEntity<Boolean> eliminar(
    		@PathVariable("idCotizacion") Integer idCotizacion) {
        if (cotizacionService.eliminar(idCotizacion)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
