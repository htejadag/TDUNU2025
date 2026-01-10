<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.request;
=======
package TDUNU2025.Msbiblioteca.model.request;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import java.util.List;

import lombok.Data;

@Data
public class LibroRequest {

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
