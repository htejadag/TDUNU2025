package unu.MsMatriculaCepre.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unu.MsMatriculaCepre.util.Turno;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoRequest {
    private String codigo;
    private String nombre;
    private Turno turno; // Solo acepta MAÑANA o TARDE
    private String aula;
    private Integer capacidadMaxima;
    private String horario;
    private String docente;
    private String procesoAcademico;
}