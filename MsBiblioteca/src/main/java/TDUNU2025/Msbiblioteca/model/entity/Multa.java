package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "multa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMulta;

    // CLAVE FORÁNEA (Usuario)
    @Column(nullable = false)
    private Long idUsuario;

    // CLAVE FORÁNEA (Prestamo)
    @Column(nullable = false)
    private Long idPrestamo;

    // Importe
    @Column(nullable = false)
    private Double monto;

    // Motivo
    @Column(length = 255)
    private String concepto;

    // Automática
    @Column(nullable = false)
    private LocalDate fechaGeneracion;

    // Si fue pagada (puede ser nulo si aún no paga)
    private LocalDate fechaPago;

    // Estado de pago (FK o ID simple)
    @Column(nullable = false)
    private Long idEstadoMulta;

    // Cálculo automático
    private Integer diasRetraso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrestamo",nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private Prestamo prestamo;
}