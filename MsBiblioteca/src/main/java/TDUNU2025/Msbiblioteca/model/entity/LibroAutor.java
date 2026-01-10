package tdunu2025.Msbiblioteca.model.entity;

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
    private Long idLibroAutor;   

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "idLibro", nullable = false)
    @ToString.Exclude
    private Libro libro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor",nullable = false)
    @ToString.Exclude
    private Autor autor;      

    private String rol;         
    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
    }
}
