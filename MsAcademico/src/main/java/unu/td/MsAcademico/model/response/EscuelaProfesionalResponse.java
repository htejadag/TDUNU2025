package unu.td.MsAcademico.model.response;

import lombok.Data;

@Data
public class EscuelaProfesionalResponse {
    private Integer id;
    private String nombre;
    private Integer duracionCarrera;
    private FacultadResponse facultad;
}
