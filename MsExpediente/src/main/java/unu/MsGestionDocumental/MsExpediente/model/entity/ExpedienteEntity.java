package unu.MsGestionDocumental.MsExpediente.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "expediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(name = "correlativo", nullable = true, length = 10)
    private String correlativo;

    @Column(name = "asunto", nullable = false, length = 4)
    private String asunto;

    @Column(name = "usuarioId", nullable = false, updatable = false)
    private Integer usuarioId;

    @Column(name = "fechaIngreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "fechaCreacion", nullable = true, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "creadoPor", nullable = true, updatable = false)
    private Integer creadoPor;

    @Column(name = "fechaEdicion")
    private LocalDateTime fechaEdicion;

    @Column(name = "editadoPor")
    private Integer editadoPor;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaEdicion = LocalDateTime.now();
    }

}