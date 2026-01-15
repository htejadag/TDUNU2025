package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "libro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Libro implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    @EqualsAndHashCode.Include
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

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @Column(length = 50)
    private String edicion;

    @Column(name = "codigo_dewey", length = 50)
    private String codigoDewey;

    @Column(name = "portada_url")
    private String portadaUrl;

    @Column(name = "archivo_digital_url")
    private String archivoDigitalUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_editorial",nullable = false)
    @ToString.Exclude
    private Editorial editorial;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_libro",nullable = false)
    @ToString.Exclude
    private EstadoLibro estadoLibro;

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<LibroCategoria> categorias; // <-- Ahora apunta a la tabla intermedia

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<DetalleLibro> detalles;

    // 4. Relación con AUTORES (Lo moví aquí para mantener orden)
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<LibroAutor> autores;
 
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @Column(name = "fecha_registro",updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate(){
        fechaActualizacion = LocalDateTime.now();
    }
}