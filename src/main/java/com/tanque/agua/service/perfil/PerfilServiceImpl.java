package com.tanque.agua.service.perfil;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanque.agua.dto.PerfilDto;
import com.tanque.agua.entity.Perfil;
import com.tanque.agua.mapper.PerfilMapper;
import com.tanque.agua.repository.PerfilRepository;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    private final PerfilMapper perfilMapper;

    public PerfilServiceImpl(PerfilRepository perfilRepository, PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
    }

    @Override
    public List<PerfilDto> buscarTodos() {
        return perfilMapper.entityToDtoList(perfilRepository.findAll());
    }

    @Override
    public PerfilDto buscarPorId(Integer idPerfil) {
        Optional<Perfil> optional = perfilRepository.findById(idPerfil);
        return optional.map(perfilMapper::entityToDTO).orElse(null);
    }

    @Override
    public PerfilDto registrar(PerfilDto perfil) {
        Perfil entity = perfilMapper.dtoToEntity(perfil);
        return perfilMapper.entityToDTO(perfilRepository.save(entity));
    }

    @Override
    public boolean eliminar(Integer idPerfil) {
        if (perfilRepository.existsById(idPerfil)) {
            perfilRepository.deleteById(idPerfil);
            return true;
        }
        return false;
    }
    
}
