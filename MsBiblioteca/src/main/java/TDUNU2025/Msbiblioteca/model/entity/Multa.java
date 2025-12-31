package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min; 
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "multa")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Multa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_multa")
    private Integer idMulta;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_prestamo", nullable = false)
    private Integer idPrestamo;

    @Min(value = 0, message = "El monto no puede ser negativo")
    @Column(nullable = false)
    private Double monto;

    @Column(length = 255)
    private String concepto;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "id_estado_multa", nullable = false)
    private Integer idEstadoMulta;

    @Column(name = "dias_retraso")
    private Integer diasRetraso;
 
    @PrePersist
    public void prePersist() {
        if (this.fechaGeneracion == null) {
            this.fechaGeneracion = LocalDate.now();
        }
    }
}