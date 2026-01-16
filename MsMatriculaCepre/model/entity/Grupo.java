package unu.MsMatriculaCepre.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unu.MsMatriculaCepre.util.Turno;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grupos")
public class Grupo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo", unique = true, nullable = false, length = 20)
    private String codigo; // GRUPO-A, GRUPO-B, etc.
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "turno", length = 20, nullable = false)
    private Turno turno; // MAÑANA o TARDE
    
    @Column(name = "aula", length = 50)
    private String aula;
    
    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;
    
    @Column(name = "capacidad_actual")
    private Integer capacidadActual = 0;
    
    @Column(name = "horario", length = 200)
    private String horario; // "Lun-Vie 8:00-12:00" o "Lun-Vie 14:00-18:00"
    
    @Column(name = "docente", length = 200)
    private String docente;
    
    @Column(name = "proceso_academico", length = 20)
    private String procesoAcademico; // 2025-III, 2026-I
    
    @Column(name = "estado", length = 20)
    private String estado = "ACTIVO"; // ACTIVO, INACTIVO, CERRADO
    
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<MatriculaGrupo> matriculasGrupo;
}