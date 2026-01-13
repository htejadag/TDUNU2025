package tdunu.MsTitulacion.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResolucionTituloResponse {
    
    private int idResolucion;
    private int idDictamen;
    private String numeroResolucion;
    private LocalDateTime fechaEmision;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
