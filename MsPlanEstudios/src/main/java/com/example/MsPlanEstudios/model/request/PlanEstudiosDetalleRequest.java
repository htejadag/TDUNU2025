package com.example.MsPlanEstudios.model.request;

import lombok.Data;

@Data
public class PlanEstudiosDetalleRequest {
    private Integer idPlanEstudio;
    private Integer idCurso;
    private Integer idCiclo;
    private Integer idTipoCursoPlan;
    private String estado;
    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
    private String fechaCreacion;
    private String fechaModificacion;
}
