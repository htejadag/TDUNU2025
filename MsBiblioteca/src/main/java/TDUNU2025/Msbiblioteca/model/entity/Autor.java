package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "autor")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idAutor;

    // CLAVE FORÁNEA (Tratada como campo simple por ahora)
    @Column(nullable = false)
    private Long idPersona;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    private LocalDate fechaFallecimiento;

    @Column(length = 255)
    private String fotoUrl;

    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<LibroAutor> libros;

}