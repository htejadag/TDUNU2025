<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.response;
=======
package TDUNU2025.Msbiblioteca.model.response;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorResponse {
    private Long idAutor;
    private Long idPersona;
    private String biografia;
    private LocalDate fechaFallecimiento;
    private String fotoUrl;
}