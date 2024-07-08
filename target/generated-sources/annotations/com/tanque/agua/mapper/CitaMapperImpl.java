package com.tanque.agua.mapper;

import com.tanque.agua.dto.CitaDto;
import com.tanque.agua.entity.Cita;
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
public class CitaMapperImpl implements CitaMapper {

    @Override
    public Cita dtoToEntity(CitaDto citaDto) {
        if ( citaDto == null ) {
            return null;
        }

        Cita.CitaBuilder cita = Cita.builder();

        if ( citaDto.getId() != null ) {
            cita.id( citaDto.getId().intValue() );
        }
        cita.name( citaDto.getName() );
        cita.email( citaDto.getEmail() );
        cita.phone( citaDto.getPhone() );
        cita.description( citaDto.getDescription() );
        cita.date( citaDto.getDate() );
        cita.hour( citaDto.getHour() );

        return cita.build();
    }

    @Override
    public CitaDto entityToDTO(Cita cita) {
        if ( cita == null ) {
            return null;
        }

        CitaDto.CitaDtoBuilder citaDto = CitaDto.builder();

        if ( cita.getId() != null ) {
            citaDto.id( cita.getId().longValue() );
        }
        citaDto.name( cita.getName() );
        citaDto.email( cita.getEmail() );
        citaDto.phone( cita.getPhone() );
        citaDto.description( cita.getDescription() );
        citaDto.date( cita.getDate() );
        citaDto.hour( cita.getHour() );

        return citaDto.build();
    }

    @Override
    public List<CitaDto> entityToDtoList(Iterable<Cita> citaList) {
        if ( citaList == null ) {
            return null;
        }

        List<CitaDto> list = new ArrayList<CitaDto>();
        for ( Cita cita : citaList ) {
            list.add( entityToDTO( cita ) );
        }

        return list;
    }
}
