package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "detalle_libro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleLibro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idLibro")
    @ToString.Exclude
    private Libro libro; 

    @Column(nullable = false)
    private Integer stockTotal;

    @Column(nullable = false)
    private Integer stockDisponible;

    private String ubicacionFisica;

    private LocalDateTime fechaActualizacion;

    @PrePersist
    @PreUpdate
    public void actualizarFecha() {
        fechaActualizacion = LocalDateTime.now();
    }
}
