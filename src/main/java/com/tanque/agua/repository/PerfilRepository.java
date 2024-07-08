package com.tanque.agua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanque.agua.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
    
}
