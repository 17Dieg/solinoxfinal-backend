package com.tanque.agua.service.usuario;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.dto.UsuarioLogin;
import com.tanque.agua.entity.Perfil;
import com.tanque.agua.entity.Usuario;
import com.tanque.agua.mapper.UsuarioMapper;
import com.tanque.agua.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final PasswordEncoder passwordEncoder;

    private static final Integer ID_PERFIL_USUARIO = 2;

    private final EntityManager entityManager;

    @Override
    public List<UsuarioDto> buscarTodos() {
        return usuarioMapper.entityToDtoList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioDto buscarPorId(Integer idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        return optional.map(usuarioMapper::entityToDTO).orElse(null);
    }

    @Override
    public UsuarioDto registrar(UsuarioDto usuario) {
        if (Objects.nonNull(usuario) && usuario.getPassword() != null && !usuario.getPassword().equals("")) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));			
		}

        Usuario entity = usuarioMapper.dtoToEntity(usuario);
        entity.setPerfil(Perfil.builder().id(ID_PERFIL_USUARIO).build());
        return usuarioMapper.entityToDTO(usuarioRepository.save(entity));
    }

    @Override
    public boolean eliminar(Integer idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }

    @Override
    public UsuarioLogin login(UsuarioLogin usuarioLogin) {
        UsuarioLogin usuario = usuarioRepository.buscarPorEmail(usuarioLogin.getEmail());

        
        if (usuario != null && passwordEncoder.matches(usuarioLogin.getPassword(), usuario.getPassword())) {
            usuario.setPassword(null);
            return usuario;
        }
        return null;
    }

    @Override
    public UsuarioDto buscarPorEmail(String email) {
        return usuarioMapper.entityToDTO(usuarioRepository.findByEmail(email));
    }

    @Override
    @Transactional
    public UsuarioDto actualizarUsuario(UsuarioDto usuario) {
        usuarioRepository.actualizarUsuario(usuario.getEmail(), usuario.getName(), 
        usuario.getPhone(), usuario.getCompany(), usuario.getIdPerfil());
        Usuario usuarioActualizado = usuarioRepository.findByEmail(usuario.getEmail());
        entityManager.refresh(usuarioActualizado); // Refresca la entidad
        return usuarioMapper.entityToDTO(usuarioActualizado);
    }
    
}
