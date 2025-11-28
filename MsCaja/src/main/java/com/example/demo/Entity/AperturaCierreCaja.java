package com.example.demo.Entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "apertura_cierre_caja")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AperturaCierreCaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAperturaCierreCaja")
    private Integer idAperturaCierreCaja;

    @NotNull(message = "No puede tener idCajero vacío")
    @Positive(message = "El total debe ser mayor que 0")
    @Column(name = "idCajero", nullable = false)
    private int idCajero;

    @Column(name = "fecha", nullable = false)
    @NotNull(message = "La fecha no puede ser nula")
    private String fecha;

    @Column(name = "monto_inicial", precision = 8, scale = 2)
    @NotNull(message = "El monto inicial no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El monto inicial debe ser cero o positivo")
    private BigDecimal montoInicial;

    @Column(name = "monto_final", precision = 8, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "El monto final debe ser cero o positivo")
    private BigDecimal montoFinal;

}
