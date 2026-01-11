<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.request;
=======
package TDUNU2025.Msbiblioteca.model.request;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroAutorRequest {

    private Long idLibro;   // FK hacia libro
    private Long idAutor;   // FK hacia autor
    private String rol;     // Autor principal, coautor, editor, etc.
}
