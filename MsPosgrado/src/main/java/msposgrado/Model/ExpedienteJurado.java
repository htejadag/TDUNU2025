package msposgrado.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Entity
@Table(name = "expediente_jurado")
@Schema(
    description = "Entidad que representa la asignación de un jurado a un expediente, incluyendo su rol y fecha de designación"
)
public class ExpedienteJurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único de la relación expediente-jurado",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idExpJurado;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-jurado")
    @Schema(
        description = "Expediente al cual se asigna el jurado"
    )
    private Expediente expediente;

    @ManyToOne
    @JoinColumn(name = "id_jurado", nullable = false)
    @Schema(
        description = "Jurado asignado al expediente"
    )
    private Jurado jurado;

    @Schema(
        description = "Rol que cumple el jurado dentro del expediente",
        example = "PRESIDENTE"
    )
    private String rol;

    @Schema(
        description = "Fecha y hora en que el jurado fue designado al expediente",
        example = "2025-01-15T10:30:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime fechaDesignacion = LocalDateTime.now();

    public Integer getIdExpJurado() {
        return idExpJurado;
    }

    public void setIdExpJurado(Integer idExpJurado) {
        this.idExpJurado = idExpJurado;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Jurado getJurado() {
        return jurado;
    }

    public void setJurado(Jurado jurado) {
        this.jurado = jurado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaDesignacion() {
        return fechaDesignacion;
    }

    public void setFechaDesignacion(LocalDateTime fechaDesignacion) {
        this.fechaDesignacion = fechaDesignacion;
    }
}