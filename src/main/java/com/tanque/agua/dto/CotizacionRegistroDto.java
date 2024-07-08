package com.tanque.agua.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionRegistroDto {
    private Integer id;

    private byte[] archivo;

    private byte[] pdf;

    private String descripcion;
    
    private String nombre;
    private String email;
    private String telefono;
    private String contrasenia;
    private String empresa;
}
