package Ms_Reingresante.Ms_Reingresante.model.request;



import java.time.LocalDate;
import lombok.Data;

@Data
public class FichaNoAdeudoRequest {

      private Integer idProceso; // id Proceso INT (Clave Foránea) [cite: 107]

    private String fichaNumero; // Ficha Numero VARCHAR(50) [cite: 108]

    private String emitidoPor; // Ficha Emitido Por VARCHAR(50) [cite: 110]

    private String usuarioCreacion; // USUARIO CREACION VARCHAR(50) [cite: 111]

    private String usuarioModificacion; // USUARIO MODIFICACION VARCHAR(5) [cite: 111]

    private LocalDate fechaEmision;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}