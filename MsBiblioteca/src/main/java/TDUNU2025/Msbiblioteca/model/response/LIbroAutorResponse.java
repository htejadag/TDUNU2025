package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroAutorResponse {

    private Long idLibroAutor;   

    private Long idLibro;        

    private Integer idAutor;        

    private String rol;          

    private LocalDateTime fechaRegistro;
}