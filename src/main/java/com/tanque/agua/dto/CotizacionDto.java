package com.tanque.agua.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionDto {
    private Integer id;

    private byte[] selected3DFile;

    private byte[] selectedPdfFile;

    private String file3D;

    private String filePdf;

    private String specifications;

    private String email;

    private Integer isLoggedIn;
    
    private UsuarioDto usuario;
}
