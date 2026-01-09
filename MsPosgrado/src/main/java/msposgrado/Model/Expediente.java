package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Entity
@Table(name = "expediente")
@Schema(
    description = "Entidad que representa el expediente académico del estudiante dentro del proceso de posgrado"
)
public class Expediente extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único del expediente",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idExpediente;

    @NotNull(message = "El id del estudiante es obligatorio")
    @Column(name = "id_estudiante", nullable = false)
    @Schema(
        description = "Identificador del estudiante asociado al expediente",
        example = "20231234"
    )
    private Integer idEstudiante;

    @NotBlank(message = "El estado del expediente es obligatorio")
    @Size(max = 50, message = "El estado no puede superar los 50 caracteres")
    @Column(name = "estado_expediente", length = 50, nullable = false)
    @Schema(
        description = "Estado actual del expediente",
        example = "INICIADO"
    )
    private String estadoExpediente;

    @Column(name = "fecha_apertura", updatable = false)
    @Schema(
        description = "Fecha y hora de apertura del expediente",
        example = "2025-01-10T09:00:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    @Schema(
        description = "Fecha y hora de cierre del expediente",
        example = "2025-06-30T18:00:00"
    )
    private LocalDateTime fechaCierre;

    @Size(max = 1000, message = "Las observaciones no pueden superar los 1000 caracteres")
    @Column(columnDefinition = "TEXT")
    @Schema(
        description = "Observaciones o comentarios adicionales sobre el expediente",
        example = "Expediente generado automáticamente al iniciar el trámite"
    )
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

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getEstadoExpediente() {
        return estadoExpediente;
    }

    public void setEstadoExpediente(String estadoExpediente) {
        this.estadoExpediente = estadoExpediente;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}