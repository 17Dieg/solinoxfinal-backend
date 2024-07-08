package com.tanque.agua.service.cotizacion;

import java.util.List;

import com.tanque.agua.dto.CotizacionDto;

public interface CotizacionService {
    
    List<CotizacionDto> buscarTodos();

    CotizacionDto buscarPorId(Integer idCotizacion);

    List<CotizacionDto> buscarPorUsuario(Integer idUsuario);

    CotizacionDto registrar (CotizacionDto cotizacion);
    
    boolean eliminar (Integer idCotizacion);
}
