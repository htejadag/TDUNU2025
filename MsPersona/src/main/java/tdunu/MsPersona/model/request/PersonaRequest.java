package tdunu.MsPersona.model.request;

import lombok.Data;

@Data
public class PersonaRequest {
    private String perNombres;
    private String perApellidos;
    private String perDni;
    private String perEmail;
    private String perTelefono;
}
