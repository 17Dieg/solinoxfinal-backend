package com.tanque.agua.mapper;

import com.tanque.agua.dto.CotizacionDto;
import com.tanque.agua.entity.Cotizacion;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CotizacionMapperImpl implements CotizacionMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Cotizacion dtoToEntity(CotizacionDto cotizacionDto) {
        if ( cotizacionDto == null ) {
            return null;
        }

        Cotizacion.CotizacionBuilder cotizacion = Cotizacion.builder();

        cotizacion.id( cotizacionDto.getId() );
        byte[] selected3DFile = cotizacionDto.getSelected3DFile();
        if ( selected3DFile != null ) {
            cotizacion.selected3DFile( Arrays.copyOf( selected3DFile, selected3DFile.length ) );
        }
        byte[] selectedPdfFile = cotizacionDto.getSelectedPdfFile();
        if ( selectedPdfFile != null ) {
            cotizacion.selectedPdfFile( Arrays.copyOf( selectedPdfFile, selectedPdfFile.length ) );
        }
        cotizacion.specifications( cotizacionDto.getSpecifications() );
        cotizacion.usuario( usuarioMapper.dtoToEntity( cotizacionDto.getUsuario() ) );

        return cotizacion.build();
    }

    @Override
    public CotizacionDto entityToDTO(Cotizacion cotizacion) {
        if ( cotizacion == null ) {
            return null;
        }

        CotizacionDto.CotizacionDtoBuilder cotizacionDto = CotizacionDto.builder();

        cotizacionDto.id( cotizacion.getId() );
        byte[] selected3DFile = cotizacion.getSelected3DFile();
        if ( selected3DFile != null ) {
            cotizacionDto.selected3DFile( Arrays.copyOf( selected3DFile, selected3DFile.length ) );
        }
        byte[] selectedPdfFile = cotizacion.getSelectedPdfFile();
        if ( selectedPdfFile != null ) {
            cotizacionDto.selectedPdfFile( Arrays.copyOf( selectedPdfFile, selectedPdfFile.length ) );
        }
        cotizacionDto.specifications( cotizacion.getSpecifications() );
        cotizacionDto.usuario( usuarioMapper.entityToDTO( cotizacion.getUsuario() ) );

        return cotizacionDto.build();
    }

    @Override
    public List<CotizacionDto> entityToDtoList(Iterable<Cotizacion> cotizacionList) {
        if ( cotizacionList == null ) {
            return null;
        }

        List<CotizacionDto> list = new ArrayList<CotizacionDto>();
        for ( Cotizacion cotizacion : cotizacionList ) {
            list.add( entityToDTO( cotizacion ) );
        }

        return list;
    }
}
