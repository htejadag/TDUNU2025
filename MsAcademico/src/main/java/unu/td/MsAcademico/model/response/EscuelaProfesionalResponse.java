package unu.td.MsAcademico.model.response;

import lombok.Data;

@Data
public class EscuelaProfesionalResponse extends EntidadAcademicaResponse{
    private Integer duracionCarrera;
    private FacultadResponse facultad;
}
