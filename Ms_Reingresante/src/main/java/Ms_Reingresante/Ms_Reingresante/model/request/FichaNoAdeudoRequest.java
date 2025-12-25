package Ms_Reingresante.Ms_Reingresante.model.request;



import java.time.LocalDateTime;   
import lombok.Data;

@Data
public class FichaNoAdeudoRequest {

     public Integer idProceso; // id Proceso INT (Clave Foránea) [cite: 107]

    public String fichaNumero; // Ficha Numero VARCHAR(50) [cite: 108]

  public LocalDateTime fechaEmision; // Ficha Fecha Emision DATE [cite: 109]

    public String emitidoPor; // Ficha Emitido Por VARCHAR(50) [cite: 110]

    public LocalDateTime fechaCreacion; // FECHA CREACION DATETIME [cite: 111]

    public String usuarioCreacion; // USUARIO CREACION VARCHAR(50) [cite: 111]

    
   public LocalDateTime fechaModificacion; // FECHA MODIFICACION DATETIME [cite: 111]

    public String usuarioModificacion; // USUARIO MODIFICACION VARCHAR(5) [cite: 111]
}