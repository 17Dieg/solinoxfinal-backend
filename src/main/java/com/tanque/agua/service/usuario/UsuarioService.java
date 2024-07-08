package com.tanque.agua.service.usuario;

import java.util.List;

import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.dto.UsuarioLogin;

public interface UsuarioService {
    
    List<UsuarioDto> buscarTodos();

    UsuarioDto buscarPorId(Integer idUsuario);

    UsuarioDto buscarPorEmail(String email);

    UsuarioDto registrar (UsuarioDto usuario);

    UsuarioDto actualizarUsuario (UsuarioDto usuario);

    UsuarioLogin login (UsuarioLogin usuarioLogin);
    
    boolean eliminar (Integer idUsuario);
}
