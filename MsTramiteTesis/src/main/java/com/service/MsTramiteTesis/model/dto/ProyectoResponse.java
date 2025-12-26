package com.service.MsTramiteTesis.model.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoResponse {

    private Long idProyecto;
    private Long idEstudianteExt;
    private Long idAsesorExt;
    private Long idEspecialidadExt;
    private String tituloProyecto;
    private String rutaPdfProyecto;
    private String estadoProyectoCodigo;
    private OffsetDateTime fechaRegistro;
    private OffsetDateTime fechaAprobacionFinal;
}