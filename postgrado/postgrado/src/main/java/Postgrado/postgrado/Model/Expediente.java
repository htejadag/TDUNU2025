package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "expediente")
@Data
public class Expediente extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpediente;

    @NotNull(message = "El id del estudiante es obligatorio")
    @Column(name = "id_estudiante", nullable = false)
    private Integer idEstudiante;

    @NotBlank(message = "El estado del expediente es obligatorio")
    @Size(max = 50, message = "El estado no puede superar los 50 caracteres")
    @Column(name = "estado_expediente", length = 50, nullable = false)
    private String estadoExpediente;

    @Column(name = "fecha_apertura", updatable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Size(max = 1000, message = "Las observaciones no pueden superar los 1000 caracteres")
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @PrePersist
    public void prePersist() {
        if (this.estadoExpediente == null) {
            this.estadoExpediente = "INICIADO";
        }
        if (this.fechaApertura == null) {
            this.fechaApertura = LocalDateTime.now();
        }
    }
}
