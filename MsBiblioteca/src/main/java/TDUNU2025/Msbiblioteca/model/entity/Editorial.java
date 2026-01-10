package tdunu2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "editorial")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Editorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial")
    private Long idEditorial;

    @Column(name="nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name="direccion", length = 200)
    private String direccion;

    @Column(name="telefono", length = 20)
    private String telefono;

    @Column(name="email", length = 100)
    private String email;

    @Column(name = "sitio_web", length = 150)
    private String sitioWeb;

    @Column(name="pais", length = 50)
    private String pais;
    
    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // Relación Inversa (Uno a Muchos) con Libro
    @OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY)
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude 
    private Set<Libro> libros;
}