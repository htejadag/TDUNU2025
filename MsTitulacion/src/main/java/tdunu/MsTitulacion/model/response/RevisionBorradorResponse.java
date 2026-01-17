package tdunu.MsTitulacion.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RevisionBorradorResponse {
    
    private int idRevisionBorrador;
    private int idJurado;
    private int idTesisBorrador;
    private String comentarios;
    private boolean aprobado;
    private LocalDateTime fechaRevision;

}
