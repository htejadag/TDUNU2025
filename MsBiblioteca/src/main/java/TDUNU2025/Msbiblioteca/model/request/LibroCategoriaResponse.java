package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroCategoriaResponse {

    private Long idLibroCategoria;      
    
    private Long idLibro;

    private Long idCategoria;
}