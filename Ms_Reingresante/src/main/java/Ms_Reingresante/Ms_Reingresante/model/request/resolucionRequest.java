package Ms_Reingresante.Ms_Reingresante.model.request;


import java.time.LocalDateTime;   
import lombok.Data;

@Data
public class resolucionRequest {
    
    public String resNumero;
    public String resCodigoActivacion;
    public  LocalDateTime resFechaEmision;
    public String resEmitidoPor;
    public String usuarioCreacion;
    public String usuarioModificacion;
    public LocalDateTime fechaModificacion;
    public LocalDateTime fechaCreacion;
    
   
}
