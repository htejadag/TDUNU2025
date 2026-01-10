package tdunu2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Set; 

@Entity
@Table(name = "autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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