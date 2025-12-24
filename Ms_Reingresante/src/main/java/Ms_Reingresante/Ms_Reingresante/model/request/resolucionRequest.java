package Ms_Reingresante.Ms_Reingresante.model.request;


import java.time.LocalDateTime;   
import lombok.Data;

@Data
public class resolucionRequest {
    
    private String resNumero;
    private String resCodigoActivacion;
    private LocalDateTime resFechaEmision;
    private String resEmitidoPor;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;  
    private String usuarioModificacion;
    private LocalDateTime fechaModificacion; 

}
