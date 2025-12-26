package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro_autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibroAutor;   // PK de la tabla intermedia

    private Long idLibro;        // FK hacia libro
    private Long idAutor;        // FK hacia autor

    private String rol;          // Autor principal, coautor, editor, etc.

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}
