package com.example.MsPlanEstudios.model.request;

import lombok.Data;

@Data
public class PlanEstudiosPrerequisitoRequest {
    private Integer idPlanEstudioDetalle;
    private Integer idCursoPrerequisito;
}
