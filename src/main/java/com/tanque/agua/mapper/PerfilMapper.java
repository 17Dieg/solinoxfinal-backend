package com.tanque.agua.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tanque.agua.dto.PerfilDto;
import com.tanque.agua.entity.Perfil;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    
    Perfil dtoToEntity(PerfilDto perfilDto);

    PerfilDto entityToDTO(Perfil perfil);

    List<PerfilDto> entityToDtoList(Iterable<Perfil> perfilList);
}
