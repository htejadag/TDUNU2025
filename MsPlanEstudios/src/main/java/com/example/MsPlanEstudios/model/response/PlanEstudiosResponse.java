package com.example.MsPlanEstudios.model.response;

import lombok.Data;

@Data
public class PlanEstudiosResponse {
    public Integer id;
    private Integer idCarrera;
    private String nombre;
    private String año;
    private boolean estado;
    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
    private String fechaCreacion;
    private String fechaModificacion;
}
