package com.tanque.agua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tanque.agua.dto.UsuarioDto;
import com.tanque.agua.dto.UsuarioLogin;
import com.tanque.agua.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findByEmail(String email);

    
    @Query(value = """
            SELECT new com.tanque.agua.dto.UsuarioLogin(
            u.name,
            u.email,
            u.phone,
            u.password,
            u.company,
            p.id,
            p.name
            ) FROM Usuario u
            LEFT JOIN Perfil p ON u.perfil.id = p.id
            WHERE u.email = ?1
            """)
    UsuarioLogin buscarPorEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.name = ?2, u.phone = ?3, u.company = ?4, u.perfil.id = ?5 WHERE u.email = ?1")
    void actualizarUsuario(String email, String name, String phone, String company, Integer idPerfil);
}
