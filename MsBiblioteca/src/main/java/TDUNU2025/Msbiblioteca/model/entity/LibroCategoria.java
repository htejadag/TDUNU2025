package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libro_categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibroCategoria; // PK

    private Long idLibro;      // FK
    private Long idCategoria;  // FK
}
