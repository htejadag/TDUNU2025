package tdunu.MsTitulacion.model.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RevisionBorradorRequest {
    
    private Integer idRevisionBorrador;
    private Integer idJurado;
    private Integer idTesisBorrador;
    private String comentarios;
    private boolean aprobado;
    private LocalDateTime fechaRevision;

}
