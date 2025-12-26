package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroAutorResponse {

    private Long idLibroAutor;   // PK
    private Long idLibro;        // FK
    private Long idAutor;        // FK
    private String rol;          // Rol del autor
    private LocalDateTime fechaRegistro;
}
