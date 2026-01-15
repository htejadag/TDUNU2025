package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "estado_libro")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true) 
public class EstadoLibro implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_libro")
    @EqualsAndHashCode.Include
    private Long idEstadoLibro;

    @Column(name="nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name="descripcion", length = 200)
    private String descripcion;
    
    @OneToMany(mappedBy = "estadoLibro", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Libro> libros;
    
    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }
}