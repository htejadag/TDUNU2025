package tdunu.MsTitulacion.model.request;

import lombok.Data;


@Data
public class RevisionBorradorRequest {
    
    private int idJurado;
    private int idTesisBorrador;
    private String comentarios;
    private boolean aprobado;

}
