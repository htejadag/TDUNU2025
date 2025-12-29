package TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;

@Data
public class LibroRequest {

    private String isbn;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private Integer numeroPaginas;
    private String idioma;
    private Integer fechaPublicacion;
    private String edicion;
    private String codigoDewey;
    private String portadaUrl;
    private String archivoDigitalUrl;
    private Long idEditorial;
    private Long idEstadoLibro;
}
