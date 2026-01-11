<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.request;
=======
package TDUNU2025.Msbiblioteca.model.request;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorRequest {
    private Long idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}