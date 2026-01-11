package TDUNU2025.Msbiblioteca.model.request;

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
