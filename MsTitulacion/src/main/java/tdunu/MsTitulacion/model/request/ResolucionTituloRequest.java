package tdunu.MsTitulacion.model.request;
import java.sql.Date;
import java.time.LocalDateTime;

public class ResolucionTituloRequest {
    
    private Integer idResolucionTitulo;
    private Integer idDictamen;
    private String numeroResolucion;
    private LocalDateTime fechaEmision;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
