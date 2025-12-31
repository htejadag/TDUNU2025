package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_libro")
    private Long idLibro;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 200)
    private String subtitulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "numero_paginas")
    private Integer numeroPaginas;

    @Column(length = 50)
    private String idioma;

    @Column(name = "fecha_publicacion")
    private Integer fechaPublicacion; 

    @Column(length = 50)
    private String edicion;

    @Column(name = "codigo_dewey", length = 50)
    private String codigoDewey;

    @Column(name = "portada_url")
    private String portadaUrl;

    @Column(name = "archivo_digital_url")
    private String archivoDigitalUrl;


    @Column(name = "id_editorial", nullable = false)
    private Long idEditorial;

    @Column(name = "id_estado_libro", nullable = false)
    private Long idEstadoLibro; 

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }
}