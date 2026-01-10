package tdunu.MsTitulacion.model.request;
import java.sql.Date;

public class ResolucionTituloRequest {
    
    private Integer idResolucionTitulo;
    private Integer idDictamen;
    private String numeroResolucion;
    private Date fechaEmision;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
