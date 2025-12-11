package com.unu.TDUNU2025.Msbiblioteca.model.entity;

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
    private Integer idDetalleLibro;

    @ManyToOne(fetch = FetchType.LAZY) // Opcional: Eager si siempre necesitas los datos del libro
    @JoinColumn(name = "idLibro", nullable = false)
    private Libro libro;
    
    @Column(nullable = false)
    private Integer stockTotal;

    @Column(nullable = false)
    private Integer stockDisponible;

    @Column(length = 100)
    private String ubicacionFisica;

    private LocalDateTime fechaActualizacion;

    @PreUpdate
    @PrePersist 
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}