package tdunu2025.msbiblioteca.model.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibroRequest {

    @NotBlank(message ="el isbn es obligatorio")
    @Size(max = 20)
    private String isbn;

    @NotBlank(message = "El titulo es Obligatorio")
    private String titulo;

    private String subtitulo;
    private String descripcion;
    
    @NotNull(message = "el numero de paginas es obligatorio")
    private Integer numeroPaginas;

    private String idioma;
    private Integer anioPublicacion;
    private String edicion;
    private String codigoDewey;
    private String portadaUrl;
    private String archivoDigitalUrl;
    
    @NotNull(message = "la editorial es obligatoria")
    private Long idEditorial;

    @NotNull(message = "el estado del libro es obligatorio")
    private Long idEstadoLibro;
    
    private List<Long> idsCategorias;

}
