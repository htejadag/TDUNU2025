package com.unu.TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleUsuario {

    @Id
    private Integer idUsuario; // Definido como PK según tu instrucción

    @Column(nullable = false)
    private Integer idDetalleUsuario; // Definido como FK según tu instrucción

    private Integer totalPrestamos;

    private Integer totalMultas;

    private LocalDate fechaUltimoPrestamo;
}