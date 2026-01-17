package com.example.MsPlanEstudios.model.request;

import lombok.Data;

@Data
public class PlanEstudiosRequest {
    private Integer idCarrera;
    private String nombre;
    private String año;
    private Boolean estado;
}
