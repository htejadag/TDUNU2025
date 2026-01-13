package Ms_Reingresante.Ms_Reingresante.model.request;


import java.time.LocalDate;
 
import lombok.Data;

@Data
public class ResolucionRequest {
    
    private String resNumero;
    private String resCodigoActivacion;
    private LocalDate resFechaEmision;
    private String resEmitidoPor;
    private String usuarioCreacion;
    private LocalDate fechaCreacion;  
    private String usuarioModificacion;
    private LocalDate fechaModificacion; 

}
