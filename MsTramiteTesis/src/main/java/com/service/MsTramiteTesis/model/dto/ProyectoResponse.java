package com.service.MsTramiteTesis.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoResponse {

    private Long idProyecto;
    private Integer idEstudiante;
    private Integer idAsesor;
    private Integer idLinea;
    private String titulo;
    private String rutaArchivoProyecto;
    private String codigoSeguimiento;
    private Integer estadoProyectoCat;
    private LocalDateTime fechaRegistro;
}