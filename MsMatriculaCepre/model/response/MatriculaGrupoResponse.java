package unu.MsMatriculaCepre.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaGrupoResponse {
    private Long id;
    private Long matriculaId;
    private String nombreEstudiante;
    private String dniEstudiante;
    private Long grupoId;
    private String codigoGrupo;
    private String nombreGrupo;
    private String turno;
    private String aula;
    private String horario;
    private String docente;
    private String estado;
    private String fechaAsignacion;
    private String observaciones;
    public void setMatriculaId(Integer matriculaId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMatriculaId'");
    }
}