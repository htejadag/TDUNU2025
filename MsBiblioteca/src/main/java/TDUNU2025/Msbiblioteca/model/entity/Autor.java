package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate; 

@Entity
@Table(name = "autor")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
public class Autor implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include 
    private Integer idAutor;

    @Column(name = "id_persona", nullable = false) 
    private Integer idPersona;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "fecha_fallecimiento")
    private LocalDate fechaFallecimiento;

    @Column(name = "foto_url", length = 255)
    private String fotoUrl;
}