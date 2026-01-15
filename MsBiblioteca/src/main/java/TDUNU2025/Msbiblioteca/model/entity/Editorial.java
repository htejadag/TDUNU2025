package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "editorial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEditorial;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 120)
    private String email;

    @Column(length = 150)
    private String sitioWeb;

    private LocalDate fechaPago;

    @Column(length = 50)
    private String pais;
}
