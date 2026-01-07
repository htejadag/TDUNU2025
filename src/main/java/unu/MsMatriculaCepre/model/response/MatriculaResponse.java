package unu.MsMatriculaCepre.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatriculaResponse {

    private Integer matriculaId;

    private String codigo;

    private Integer estudianteId;

    private Integer grupoId;

    private LocalDateTime fechaMatricula;

    private String estado;

    private String observaciones;

    private String sistemaOrigen;
}
