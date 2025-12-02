package com.example.MsGeneral.Model.Entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UnidadOrganica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadOrganica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String siglas;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(length = 50, nullable = false)
    private String usuarioCreacion;

    @Column()
    private LocalDateTime fechaModificacion;

    @Column(length = 50)
    private String usuarioModificacion;

    // AUDITORÍA AUTOMÁTICA
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.usuarioCreacion = "SYSTEM";
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDateTime.now();
        if (this.usuarioModificacion == null) {
            this.usuarioModificacion = "SYSTEM";
        }
    }
}
