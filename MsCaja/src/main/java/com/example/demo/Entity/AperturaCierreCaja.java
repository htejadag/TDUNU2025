package com.example.demo.Entity;

import java.time.LocalDate;
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
    private LocalDate fecha;

    @Column(name = "monto_inicial")
    @NotNull(message = "El monto inicial no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El monto inicial debe ser cero o positivo")
    private double montoInicial;

    @Column(name = "monto_final")
    @DecimalMin(value = "0.0", inclusive = true, message = "El monto final debe ser cero o positivo")
    private double montoFinal;

}
