package tdunu.MsTitulacion.model.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;
public class RevisionBorradorRequest {
    
    private Integer idRevisionBorrador;
    private Integer idJurado;
    private Integer idTesisBorrador;
    private String comentarios;
    private boolean aprobado;
    private LocalDateTime fechaRevision;

}
