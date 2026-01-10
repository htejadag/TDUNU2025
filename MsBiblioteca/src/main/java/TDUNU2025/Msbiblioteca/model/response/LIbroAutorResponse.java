<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.response;
=======
package TDUNU2025.Msbiblioteca.model.response;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroAutorResponse {

    private Long idLibroAutor;
    private Long idLibro;     
    private Long idAutor;     
    private String rol;       
    private LocalDateTime fechaRegistro;
}
