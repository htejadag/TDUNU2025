package tdunu.MsTitulacion.model.response;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RevisionBorradorResponse {
    
    private Integer idRevisionBorrador;
    private Integer idJurado;
    private Integer idTesisBorrador;
    private String comentarios;
    private boolean aprobado;
    private LocalDateTime fechaRevision;

}
