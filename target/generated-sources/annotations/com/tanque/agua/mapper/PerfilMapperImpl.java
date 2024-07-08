package com.tanque.agua.mapper;

import com.tanque.agua.dto.PerfilDto;
import com.tanque.agua.entity.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T13:01:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class PerfilMapperImpl implements PerfilMapper {

    @Override
    public Perfil dtoToEntity(PerfilDto perfilDto) {
        if ( perfilDto == null ) {
            return null;
        }

        Perfil.PerfilBuilder perfil = Perfil.builder();

        perfil.id( perfilDto.getId() );
        perfil.name( perfilDto.getName() );

        return perfil.build();
    }

    @Override
    public PerfilDto entityToDTO(Perfil perfil) {
        if ( perfil == null ) {
            return null;
        }

        PerfilDto.PerfilDtoBuilder perfilDto = PerfilDto.builder();

        perfilDto.id( perfil.getId() );
        perfilDto.name( perfil.getName() );

        return perfilDto.build();
    }

    @Override
    public List<PerfilDto> entityToDtoList(Iterable<Perfil> perfilList) {
        if ( perfilList == null ) {
            return null;
        }

        List<PerfilDto> list = new ArrayList<PerfilDto>();
        for ( Perfil perfil : perfilList ) {
            list.add( entityToDTO( perfil ) );
        }

        return list;
    }
}
