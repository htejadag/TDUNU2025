package com.unu.TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(nullable = false, unique = true)
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

    private Long idEditorial;     // FK
    private Long idEstadoLibro;   // FK catálogo

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}
