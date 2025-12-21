package tdunu.MsPersona.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DocenteResponse {
    private Integer idDocente;
    private String docNombres;
    private String docApellidos;
    private String docDni;
    private String docEmail;
    private String docTelefono;
    private String docCodigo;
    private String docFacultad;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}