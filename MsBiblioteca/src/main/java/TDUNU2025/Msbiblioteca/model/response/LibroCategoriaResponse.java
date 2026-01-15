package tdunu2025.msbiblioteca.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LibroCategoriaResponse {

    private Long id;   
    private Long idLibro;
    private Long idCategoria;
}
