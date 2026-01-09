package msposgrado.Model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Schema(
    description = "Clase base de auditoría que registra información automática de creación, " +
                "modificación y estado lógico de las entidades"
)
public abstract class AuditoriaEntity {

    @CreatedDate
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Schema(
        description = "Fecha y hora de creación del registro",
        example = "2025-01-15T10:30:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    @Schema(
        description = "Fecha y hora de la última modificación del registro",
        example = "2025-01-20T15:45:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime fechaModificacion;

    @CreatedBy
    @Column(name = "creado_por", length = 100)
    @Schema(
        description = "Usuario que creó el registro",
        example = "admin",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String creadoPor;

    @LastModifiedBy
    @Column(name = "modificado_por", length = 100)
    @Schema(
        description = "Usuario que realizó la última modificación",
        example = "admin",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String modificadoPor;

    @Column(nullable = false)
    @Schema(
        description = "Indica si el registro se encuentra activo (eliminación lógica)",
        example = "true"
    )
    private Boolean activo = true;

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}