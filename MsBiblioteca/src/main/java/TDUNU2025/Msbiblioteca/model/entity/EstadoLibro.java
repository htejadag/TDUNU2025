<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.entity;
=======
package TDUNU2025.Msbiblioteca.model.entity;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "estado_libro")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class EstadoLibro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoLibro;

    // Nombre del estado (ej. "ACTIVO", "AGOTADO"). Debe ser único.
    @Column(name="nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    // Descripción opcional del estado
    @Column(name="descripcion", length = 200)
    private String descripcion;
    
    // --- Relación Inversa (opcional, pero recomendada) ---
    // Mapeo inverso a la tabla Libro (Muchos Libros tienen Un Estado)
    @OneToMany(mappedBy = "estadoLibro", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Libro> libros;
    
    // --- Auditoría ---
    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }
}