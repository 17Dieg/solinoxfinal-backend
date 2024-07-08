package com.tanque.agua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanque.agua.dto.PanelDto;
import com.tanque.agua.service.panel.PanelService;

@RestController
@RequestMapping("/panel")
public class PanelController {
    
    @Autowired
    private PanelService panelService;

    @GetMapping("/{email}")
    public ResponseEntity<PanelDto> buscarInfo(
    		@PathVariable("email") String email) {
    	return new ResponseEntity<>(panelService
    			.buscarInfoPorEmail(email), HttpStatus.OK);
    }
}
