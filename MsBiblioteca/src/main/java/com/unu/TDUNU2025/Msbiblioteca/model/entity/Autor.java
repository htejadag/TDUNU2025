package com.unu.TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate; 

@Entity
@Table(name = "autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    // CLAVE FORÁNEA (Tratada como campo simple por ahora)
    @Column(nullable = false)
    private Integer idPersona;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    private LocalDate fechaFallecimiento;

    @Column(length = 255)
    private String fotoUrl;
}