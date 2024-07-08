package com.tanque.agua.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.entity.Usuario;

@Mapper(componentModel = "spring", uses = {PerfilMapper.class})
public interface UsuarioMapper {
    
    Usuario dtoToEntity(UsuarioDto usuarioDto);

    UsuarioDto entityToDTO(Usuario usuario);

    List<UsuarioDto> entityToDtoList(Iterable<Usuario> usuarioList);
}
