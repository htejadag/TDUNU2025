package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min; 
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_libro")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_detalle_libro")
    private Long idDetalleLibro;

    @Column(name = "id_libro", nullable = false)
    private Long idLibro; 

    @Min(value = 0, message = "El stock total no puede ser negativo")
    @Column(name = "stock_total", nullable = false)
    private Integer stockTotal;

    @Min(value = 0, message = "El stock disponible no puede ser negativo")
    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible;

    @Column(name = "ubicacion_fisica", length = 150)
    private String ubicacionFisica;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        this.fechaActualizacion = LocalDateTime.now();
        if (this.stockDisponible == null) {
            this.stockDisponible = this.stockTotal;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}