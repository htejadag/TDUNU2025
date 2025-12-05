package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consejo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consejo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consejo")
    private Integer idConsejo;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "consejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MiembroConsejo> miembros;

    @OneToMany(mappedBy = "consejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SesionConsejo> sesiones;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }
}
