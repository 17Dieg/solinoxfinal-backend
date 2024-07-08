package com.tanque.agua.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLogin {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String company;
    private Integer idPerfil;

    private String nombrePerfil;
}
