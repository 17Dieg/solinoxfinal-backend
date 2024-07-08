package com.tanque.agua.service.cita;

import java.util.List;

import com.tanque.agua.dto.CitaDto;

public interface CitaService {
    
    List<CitaDto> buscarTodos();

    List<CitaDto> buscarCitasPorEmail(String email);

    CitaDto buscarPorId(Integer idCita);
    

    boolean existeCitaPorFechaYHora(CitaDto cita);

    CitaDto registrar (CitaDto cita);
    
    boolean eliminar (Integer idCita);
}
