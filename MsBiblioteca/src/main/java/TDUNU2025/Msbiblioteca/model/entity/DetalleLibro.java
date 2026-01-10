package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "detalleLibro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleLibro;

    //-- instanciando Libro--//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLibro", nullable = false) 
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
