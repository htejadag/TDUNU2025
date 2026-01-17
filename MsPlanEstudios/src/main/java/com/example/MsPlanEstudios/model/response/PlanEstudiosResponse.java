package com.example.MsPlanEstudios.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PlanEstudiosResponse {
    public Integer id;
    private Integer idCarrera;
    private String nombre;
    private String año;
    private Boolean estado;
    private String usuarioCreacion;
    private String usuarioModificacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}
