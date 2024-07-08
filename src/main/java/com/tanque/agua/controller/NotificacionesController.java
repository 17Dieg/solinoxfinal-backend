package com.tanque.agua.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanque.agua.service.notificaciones.NotificacionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificacionesController {

    private final NotificacionService notificacionesService;

    @GetMapping("/")
    public void enviarNotificaciones() {
        notificacionesService.enviarMensajes();
    }
}