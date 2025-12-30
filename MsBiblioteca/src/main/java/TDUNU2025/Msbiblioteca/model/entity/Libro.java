package TDUNU2025.Msbiblioteca.model.entity;

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

    private Integer fechaPublicacion;
    private String edicion;
    private String codigoDewey;

    private String portadaUrl;
    private String archivoDigitalUrl;

    // **********************************************
    // ********* IMPLEMENTACIÓN DE RELACIONES *********
    // **********************************************

    // 1. Relación con EDITORIAL (Muchos Libros a Una Editorial)
    // Mantenemos el campo Long para uso directo y mapeamos el objeto.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEditorial", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Editorial editorial;

    // 2. Relación con ESTADO_LIBRO (Catálogo - Muchos Libros a Un Estado)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoLibro", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EstadoLibro estadoLibro;

    // 3. Relación con CATEGORIA (Muchos a Muchos)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "libro_categoria", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "idLibro"), inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CategoriaLibro> categorias;

    // 4. Relación con DETALLE_LIBRO (Uno a Muchos) - Opcional, pero completa el
    // modelo
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<DetalleLibro> detalles;

    // **********************************************
    // ********* CAMPOS DE CLAVE FORÁNEA SIMPLES *********
    // **********************************************
    // Mantener los campos Long es opcional, pero ayuda a manejar IDs en DTOs.
    // JPA los usará como columnas de la FK en la tabla.
    private Long idEditorial; // FK simple
    private Long idEstadoLibro; // FK catálogo simple

    // **********************************************
    // ********* CAMPOS DE AUDITORÍA *********
    // **********************************************
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