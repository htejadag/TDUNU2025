package unu.MsMatriculaCepre.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatriculaResponse {

    private Long id;
    private Integer matriculaId; 
    private String nombreEstudiante;
    private String dniEstudiante;
    private Long grupoId;
    private String codigoGrupo;
    private String nombreGrupo;
    private String turno;
    private String aula;
    private String horario;
    private String docente;
    private String estado;
    private String fechaAsignacion;
    private String observaciones;
}
