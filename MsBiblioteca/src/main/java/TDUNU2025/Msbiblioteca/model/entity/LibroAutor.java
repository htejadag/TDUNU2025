package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro_autor", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_libro","id_autor"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class LibroAutor implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_libro_autor")
    private Long idLibroAutor;   

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    @ToString.Exclude
    private Libro libro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor",nullable = false)
    @ToString.Exclude
    private Autor autor;      

    @Column(length = 50)
    private String rol;      

    @Column(name ="fecha_registro",updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}
