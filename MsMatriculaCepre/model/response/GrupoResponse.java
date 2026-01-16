package unu.MsMatriculaCepre.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private String turno;
    private String aula;
    private Integer capacidadMaxima;
    private Integer capacidadActual;
    private Integer cuposDisponibles;
    private String horario;
    private String docente;
    private String procesoAcademico;
    private String estado;
}