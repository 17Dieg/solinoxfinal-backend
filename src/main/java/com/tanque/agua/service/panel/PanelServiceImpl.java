package com.tanque.agua.service.panel;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tanque.agua.dto.CitaDto;
import com.tanque.agua.dto.PanelDto;
import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.service.cita.CitaService;
import com.tanque.agua.service.cotizacion.CotizacionService;
import com.tanque.agua.service.usuario.UsuarioService;

@Service
public class PanelServiceImpl implements PanelService {

    private final CitaService citaService;
    private final CotizacionService cotizacionService;
    private final UsuarioService usuarioService;

    private static final Integer ID_PERFIL_ADMINISTRADOR = 1;
    private static final Integer ID_PERFIL_USUARIO = 2;


    public PanelServiceImpl(CitaService citaService, CotizacionService cotizacionService, UsuarioService usuarioService) {
        this.citaService = citaService;
        this.cotizacionService = cotizacionService;
        this.usuarioService = usuarioService;
    }

    @Override
    public PanelDto buscarInfoPorEmail(String email) {
        UsuarioDto usuario = usuarioService.buscarPorEmail(email);
        if(usuario == null) {
            throw new RuntimeException("No existe el usuario");
        }

        if(usuario.getPerfil().getId().equals(ID_PERFIL_ADMINISTRADOR)){
            return PanelDto.builder()
                    .citas(citaService.buscarTodos())
                    .cotizaciones(cotizacionService.buscarTodos())
                    .usuario(usuario)
                    .build();
        } else if(usuario.getPerfil().getId().equals(ID_PERFIL_USUARIO)){

            List<CitaDto> citas = citaService.buscarCitasPorEmail(email);
            return PanelDto.builder()
                    .citas(citas)
                    .cotizaciones(cotizacionService.buscarPorUsuario(usuario.getId()))
                    .usuario(usuario)
                    .build();
        } else {
            throw new RuntimeException("Perfil no valido");
        }

    }
    
}
