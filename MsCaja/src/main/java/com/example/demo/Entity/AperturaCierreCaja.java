package com.example.demo.Entity;

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
    @Column(name = "idCliente", nullable = false)
    private int idCliente;
}
