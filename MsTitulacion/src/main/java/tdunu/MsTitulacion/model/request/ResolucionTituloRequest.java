package tdunu.MsTitulacion.model.request;

import lombok.Data;

@Data
public class ResolucionTituloRequest {
    
    private int idDictamen;
    private String numeroResolucion;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
