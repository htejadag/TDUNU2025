package tdunu.MsPersona.model.request;

import lombok.Data;

@Data
public class DocenteRequest {
    private String docNombres;
    private String docApellidos;
    private String docDni;
    private String docEmail;
    private String docTelefono;
    private String docCodigo;
    private String docFacultad;
}