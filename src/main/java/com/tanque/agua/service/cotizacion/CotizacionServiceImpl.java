package com.tanque.agua.service.cotizacion;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanque.agua.dto.CotizacionDto;
import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.entity.Cotizacion;
import com.tanque.agua.mapper.CotizacionMapper;
import com.tanque.agua.mapper.UsuarioMapper;
import com.tanque.agua.repository.CotizacionRepository;
import com.tanque.agua.service.notificaciones.NotificacionService;
import com.tanque.agua.service.usuario.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CotizacionServiceImpl implements CotizacionService {

    private final CotizacionRepository cotizacionRepository;

    private final CotizacionMapper cotizacionMapper;

    private final UsuarioMapper usuarioMapper;

    private final UsuarioService usuarioService;

    private final NotificacionService notificacionService;

    @Override
    public List<CotizacionDto> buscarTodos() {
        return cotizacionMapper.entityToDtoList(cotizacionRepository.findAll());
    }

    @Override
    public CotizacionDto buscarPorId(Integer idCotizacion) {
        Optional<Cotizacion> optional = cotizacionRepository.findById(idCotizacion);
        return optional.map(cotizacionMapper::entityToDTO).orElse(null);
    }

    @Override
    public CotizacionDto registrar(CotizacionDto cotizacion) {

        if(cotizacion.getIsLoggedIn() == 2){
            throw new RuntimeException("No se puede registrar una cotización sin estar logueado");
        }
        UsuarioDto usuario = usuarioService.buscarPorEmail(cotizacion.getEmail());

        if(usuario == null){
            throw new RuntimeException("El usuario no existe");
        }

        Cotizacion entity = cotizacionMapper.dtoToEntity(cotizacion);
        entity.setSelected3DFile(Base64.getDecoder().decode(cotizacion.getFile3D()));
        entity.setSelectedPdfFile(Base64.getDecoder().decode(cotizacion.getFilePdf()));
        entity.setUsuario(usuarioMapper.dtoToEntity(usuario));
        Cotizacion cotizacionGuardada = cotizacionRepository.save(entity);

        notificacionService.enviarCotizacion(cotizacion.getEmail());

        return cotizacionMapper.entityToDTO(cotizacionGuardada);
    }

    @Override
    public boolean eliminar(Integer idCotizacion) {
        if (cotizacionRepository.existsById(idCotizacion)) {
            cotizacionRepository.deleteById(idCotizacion);
            return true;
        }
        return false;
    }

    @Override
    public List<CotizacionDto> buscarPorUsuario(Integer idUsuario) {

        return cotizacionMapper.entityToDtoList(cotizacionRepository.findByUsuarioId(idUsuario));

    }
    
}
