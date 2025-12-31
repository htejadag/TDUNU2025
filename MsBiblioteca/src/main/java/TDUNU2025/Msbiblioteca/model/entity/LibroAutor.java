package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro_autor", uniqueConstraints = {

    @UniqueConstraint(columnNames = {"id_libro", "id_autor"}) 
})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LibroAutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_libro_autor")
    private Long idLibroAutor;

    @Column(name = "id_libro", nullable = false)
    private Long idLibro; 

    @Column(name = "id_autor", nullable = false)
    private Integer idAutor; 

    @Column(length = 50)
    private String rol; 

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }
}