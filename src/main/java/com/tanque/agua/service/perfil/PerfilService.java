package com.tanque.agua.service.perfil;

import java.util.List;

import com.tanque.agua.dto.PerfilDto;

public interface PerfilService {
    
    List<PerfilDto> buscarTodos();

    PerfilDto buscarPorId(Integer idPerfil);

    PerfilDto registrar (PerfilDto perfil);
    
    boolean eliminar (Integer idPerfil);
}
