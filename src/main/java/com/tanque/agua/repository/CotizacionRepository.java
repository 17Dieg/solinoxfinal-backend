package com.tanque.agua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanque.agua.entity.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer>{
    
    Iterable<Cotizacion> findByUsuarioId(Integer idUsuario);
}
