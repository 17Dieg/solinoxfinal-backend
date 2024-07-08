package com.tanque.agua.mapper;

import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T13:01:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired
    private PerfilMapper perfilMapper;

    @Override
    public Usuario dtoToEntity(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.id( usuarioDto.getId() );
        usuario.name( usuarioDto.getName() );
        usuario.email( usuarioDto.getEmail() );
        usuario.phone( usuarioDto.getPhone() );
        usuario.password( usuarioDto.getPassword() );
        usuario.company( usuarioDto.getCompany() );
        usuario.perfil( perfilMapper.dtoToEntity( usuarioDto.getPerfil() ) );

        return usuario.build();
    }

    @Override
    public UsuarioDto entityToDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDto.UsuarioDtoBuilder usuarioDto = UsuarioDto.builder();

        usuarioDto.id( usuario.getId() );
        usuarioDto.name( usuario.getName() );
        usuarioDto.email( usuario.getEmail() );
        usuarioDto.phone( usuario.getPhone() );
        usuarioDto.password( usuario.getPassword() );
        usuarioDto.company( usuario.getCompany() );
        usuarioDto.perfil( perfilMapper.entityToDTO( usuario.getPerfil() ) );

        return usuarioDto.build();
    }

    @Override
    public List<UsuarioDto> entityToDtoList(Iterable<Usuario> usuarioList) {
        if ( usuarioList == null ) {
            return null;
        }

        List<UsuarioDto> list = new ArrayList<UsuarioDto>();
        for ( Usuario usuario : usuarioList ) {
            list.add( entityToDTO( usuario ) );
        }

        return list;
    }
}
