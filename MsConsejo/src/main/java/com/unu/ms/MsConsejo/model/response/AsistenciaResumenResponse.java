package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaResumenResponse {

    private Integer idSesion;
    private String numeroSesion;
    private Integer totalMiembros;
    private Integer presentes;
    private Integer ausentes;
    private Integer justificados;
    private Double porcentajeAsistencia;

}
