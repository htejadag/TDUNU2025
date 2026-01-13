package tdunu.MsTitulacion.model.response;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResolucionTituloResponse {
    
    private Integer idResolucionTitulo;
    private Integer idDictamen;
    private String numeroResolucion;
    private LocalDateTime fechaEmision;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
