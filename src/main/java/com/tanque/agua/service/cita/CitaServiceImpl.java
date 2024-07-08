package com.tanque.agua.service.cita;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tanque.agua.dto.CitaDto;
import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.entity.Cita;
import com.tanque.agua.entity.Usuario;
import com.tanque.agua.exception.RequestException;
import com.tanque.agua.mapper.CitaMapper;
import com.tanque.agua.repository.CitaRepository;
import com.tanque.agua.service.notificaciones.NotificacionService;
import com.tanque.agua.service.usuario.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    private final CitaMapper citaMapper;

    private final NotificacionService notificacionService;

    private final UsuarioService usuarioService;

    @Override
    public List<CitaDto> buscarTodos() {
        return citaMapper.entityToDtoList(citaRepository.findAll());
    }

    @Override
    public CitaDto buscarPorId(Integer idCita) {
        Optional<Cita> optional = citaRepository.findById(idCita);
        return optional.map(citaMapper::entityToDTO).orElse(null);
    }

    @Override
    public CitaDto registrar(CitaDto cita) { 

        try {
            Cita entity = citaMapper.dtoToEntity(cita);
            Cita citaGuardada = citaRepository.save(entity);
            UsuarioDto  usuarioDto= usuarioService.buscarPorEmail(cita.getEmail());
            String nombresUsuario = "";
            if(usuarioDto!= null){
                nombresUsuario = usuarioDto.getName();
            }

            notificacionService.enviarCita(cita.getDate(), cita.getHour(), nombresUsuario, cita.getEmail());

            return citaMapper.entityToDTO(citaGuardada);
        } catch (Exception e) {
            throw new RequestException(HttpStatus.BAD_GATEWAY.value(),  "Ya está reservada esa hora, seleccione otra por favor.");
        }
    }

    @Override
    public boolean eliminar(Integer idCita) {
        if (citaRepository.existsById(idCita)) {
            citaRepository.deleteById(idCita);
            return true;
        }
        return false;
    }

    @Override
    public boolean existeCitaPorFechaYHora(CitaDto cita) {
        return citaRepository.existsByDateAndHour(cita.getDate(), cita.getHour());
    }

    @Override
    public List<CitaDto> buscarCitasPorEmail(String email) {
        return citaMapper.entityToDtoList(citaRepository.findByEmail(email));
    }
    
}
