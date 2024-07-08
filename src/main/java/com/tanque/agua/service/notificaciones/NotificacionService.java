package com.tanque.agua.service.notificaciones;

import java.time.LocalDate;

public interface NotificacionService {
    
    void enviarMensajes();

    void enviarCita(LocalDate fecha, String hora, String nombre, String correo);

    void enviarCotizacion(String correo);
}
