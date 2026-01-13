package tdunu.MsPersona.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PersonaResponse {
    private Integer idPersona;
    private String perNombres;
    private String perApellidos;
    private String perDni;
    private String perEmail;
    private String perTelefono;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}
