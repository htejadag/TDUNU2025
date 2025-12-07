package tdunu.MsPersona.model.response;

import lombok.Data;

@Data
public class EstudianteResponse {
    private Integer idEstudiante;
    private String estNombres;
    private String estApellidos;
    private String estDni;
    private String estEmail;
    private String estTelefono;
    private String estCodigo;
    private String estFacultad;
    private String estEscuelaProfesional;
    private String estCicloRelativo;
    private java.time.LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private java.time.LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}