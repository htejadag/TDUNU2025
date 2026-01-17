package unu.td.MsAcademico.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EscuelaProfesionalResponse extends EntidadAcademicaResponse{
    private Integer duracionCarrera;
    private FacultadResponse facultad;
}
