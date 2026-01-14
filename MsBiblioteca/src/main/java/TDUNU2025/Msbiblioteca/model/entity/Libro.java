package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

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
    private Integer anioPublicacion;
    private String edicion;
    private String codigoDewey;
    private String portadaUrl;
    private String archivoDigitalUrl;

    // ********* IMPLEMENTACIÓN DE RELACIONES *********

    // 1. Relación con EDITORIAL (Correcto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEditorial", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Editorial editorial;

    // 2. Relación con ESTADO_LIBRO (CORREGIDO)
    // Agregamos insertable=false, updatable=false para evitar conflicto con el Long de abajo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoLibro", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EstadoLibro estadoLibro;

    // 3. Relación con CATEGORIA
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LibroCategoria> categorias; // <-- Ahora apunta a la tabla intermedia

    // 4. Relación con DETALLE_LIBRO
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<DetalleLibro> detalles;

    // 5. Relación con AUTORES (Lo moví aquí para mantener orden)
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LibroAutor> autores;


    // ********* CAMPOS DE CLAVE FORÁNEA SIMPLES *********
    // Estos campos controlan la inserción en la BD
    private Long idEditorial; 
    private Long idEstadoLibro; 


    // ********* CAMPOS DE AUDITORÍA *********
    private LocalDateTime fechaActualizacion;
    
    @Column(updatable = false)
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