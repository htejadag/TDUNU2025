
package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoriaLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idCategoria;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column (length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activo;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;    

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
        if (this.activo == null){
            this.activo = true;
        }
    }
    
    @PreUpdate
    public void preUpdate(){
        fechaActualizacion = LocalDateTime.now();
    }

    // Opcional: Relación inversa 
    @OneToMany(mappedBy = "categoriaLibro",fetch = FetchType.LAZY)
    @ToString.Exclude // Evita StackOverflowError en toString()
    //@EqualsAndHashCode.Exclude
    private Set<LibroCategoria> librosAsignados;
}