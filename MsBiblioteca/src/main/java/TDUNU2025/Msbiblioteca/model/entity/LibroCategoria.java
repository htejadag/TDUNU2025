package tdunu2025.msbiblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libro_categoria",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_libro","id_categoria"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class LibroCategoria implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro_categoria")
    @EqualsAndHashCode.Include
    private Long idLibroCategoria; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    @ToString.Exclude
    private Libro libro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria",nullable = false)
    @ToString.Exclude
    private CategoriaLibro categoriaLibro;
    
    @Column(updatable = false)
    private LocalDateTime fechaAsignacion;

    @PrePersist
    public void prePersist(){
        fechaAsignacion = LocalDateTime.now();
    }
}
