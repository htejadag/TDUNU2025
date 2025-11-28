package com.unu.MsBiblioteca.model;

import lombok.Data;

@Data
public class LibroResponse {
    private Long id;
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
