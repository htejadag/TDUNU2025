package tdunu2025.Msbiblioteca.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libro_categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibroCategoria; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLibro", nullable = false)
    @ToString.Exclude
    private Libro libro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria",nullable = false)
    private CategoriaLibro categoriaLibro;
    
    @Column(updatable = false)
    private LocalDateTime fechaAsignacion;

    @PrePersist
    public void prePersist(){
        fechaAsignacion = LocalDateTime.now();
    }
}
