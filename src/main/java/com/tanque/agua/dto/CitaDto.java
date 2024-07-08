package com.tanque.agua.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDto {
    
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String description;

    private LocalDate date;

    private String hour;
}
