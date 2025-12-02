package com.example.MsGeneral.Model.Entidad;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Catalogo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCatalogo;

    @Column(length = 100, nullable = false)
    private String nombreTabla;

    @Column(length = 32, nullable = false)
    private String codigo;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(length = 20)
    private String abreviatura;

    @Column(nullable = false)
    private Integer orden;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(length = 50, nullable = false)
    private String usuarioCreacion;

    @Column()
    private LocalDateTime fechaModificacion;

    @Column(length = 50)
    private String usuarioModificacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.usuarioCreacion = "SYSTEM";
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SYSTEM";
    }
}
