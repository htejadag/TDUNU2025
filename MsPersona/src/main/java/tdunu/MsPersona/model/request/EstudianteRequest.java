package tdunu.MsPersona.model.request;

import lombok.Data;

@Data
public class EstudianteRequest {
    private String estNombres;
    private String estApellidos;
    private String estDni;
    private String estEmail;
    private String estTelefono;
    private String estCodigo;
    private String estFacultad;
    private String estEscuelaProfesional;
    private String estCicloRelativo;
}