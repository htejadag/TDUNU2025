package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "multa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Multa implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    @EqualsAndHashCode.Include
    private Long idMulta;

    @Column(name = "id_usuario",nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    @Min(value = 0, message = "El valor no puede ser negativo")
    private BigDecimal monto;

    @Column(length = 255)
    private String concepto;

    @Column(name = "fecha_generacion" ,nullable = false)
    private LocalDateTime fechaGeneracion;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "id_estado_multa",nullable = false)
    private Long idEstadoMulta;

    @Column(name = "dias_retraso")
    private Integer diasRetraso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestamo",nullable = false)
    @ToString.Exclude
    private Prestamo prestamo;

    @PrePersist
    public void prePersist(){
        if (this.fechaGeneracion == null){
            this.fechaGeneracion = LocalDateTime.now();
        }
    }
}