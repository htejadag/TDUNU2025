package tdunu2025.msbiblioteca.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LibroResponse {
    private Long idLibro;
    private String isbn;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private Integer numeroPaginas;
    private String idioma;
    private Integer anioPublicacion;
    private String edicion;
    private String codigoDewey;
    private String portadaUrl;
    private String archivoDigitalUrl;
    private Long idEditorial;
    private Long idEstadoLibro;

    private List<Long> idsCategorias;
}
