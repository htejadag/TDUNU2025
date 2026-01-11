package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroCategoriaRequest {

    private Long idLibroCategoria;      
    
    private Long idLibro;

    private Long idCategoria;
}