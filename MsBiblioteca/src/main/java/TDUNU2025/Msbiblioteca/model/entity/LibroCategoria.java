package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "libro_categoria", uniqueConstraints = {

        @UniqueConstraint(columnNames = { "id_libro", "id_categoria" })
})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LibroCategoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_libro_categoria")
    private Long idLibroCategoria;

    @Column(name = "id_libro", nullable = false)
    private Long idLibro;

    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;
}