package com.tanque.agua.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanque.agua.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer>{
    
    boolean existsByDateAndHour(LocalDate fecha, String hora);

    List<Cita> findByEmail(String email);
    
}
