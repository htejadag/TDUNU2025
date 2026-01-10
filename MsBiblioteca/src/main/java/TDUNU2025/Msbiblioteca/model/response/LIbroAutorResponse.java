package tdunu2025.msbiblioteca.model.response;

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
