package com.tanque.agua.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tanque.agua.dto.CotizacionDto;
import com.tanque.agua.entity.Cotizacion;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface CotizacionMapper {
    
    Cotizacion dtoToEntity(CotizacionDto cotizacionDto);

    CotizacionDto entityToDTO(Cotizacion cotizacion);

    List<CotizacionDto> entityToDtoList(Iterable<Cotizacion> cotizacionList);
}
