package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;    

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate(){
        fechaActualizacion = LocalDateTime.now();
    }

    // Opcional: Relación inversa 
    @ManyToMany(mappedBy = "categoria")
    @ToString.Exclude // Evita StackOverflowError en toString()
    private Set<Libro> libros;
}