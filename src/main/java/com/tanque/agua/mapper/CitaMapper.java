package com.tanque.agua.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tanque.agua.dto.CitaDto;
import com.tanque.agua.entity.Cita;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    
    Cita dtoToEntity(CitaDto citaDto);

    CitaDto entityToDTO(Cita cita);

    List<CitaDto> entityToDtoList(Iterable<Cita> citaList);
}
