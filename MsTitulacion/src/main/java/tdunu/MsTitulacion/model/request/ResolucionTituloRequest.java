package tdunu.MsTitulacion.model.request;
import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResolucionTituloRequest {
    
    private Integer idResolucionTitulo;
    private Integer idDictamen;
    private String numeroResolucion;
    private LocalDateTime fechaEmision;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}
