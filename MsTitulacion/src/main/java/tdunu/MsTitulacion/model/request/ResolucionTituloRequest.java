package tdunu.MsTitulacion.model.request;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResolucionTituloRequest {
    
    private int idDictamen;
    private String numeroResolucion;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
