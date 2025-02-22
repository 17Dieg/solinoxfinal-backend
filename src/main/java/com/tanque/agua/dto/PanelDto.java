package com.tanque.agua.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanelDto {
    private List<CitaDto> citas;

    private List<CotizacionDto> cotizaciones;

    private UsuarioDto usuario;
}
