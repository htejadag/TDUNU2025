package com.example.MsPlanEstudios.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PlanEstudiosDetalleResponse {
    public Integer id;
    private Integer idPlanEstudio;
    private Integer idCurso;
    private Integer idCiclo;
    private Integer idTipoCursoPlan;
    private Boolean estado;
    private String usuarioCreacion;
    private String usuarioModificacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Integer creditos;
    private Integer horasTeoricas;
    private Integer horasPracticas;
    private Integer ordenEnCiclo;
    private List<Integer> prerrequisitos;
}
