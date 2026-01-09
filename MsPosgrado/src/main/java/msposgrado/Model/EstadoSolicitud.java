package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "estado_solicitud")
@Schema(
    description = "Entidad que representa el estado de una solicitud dentro del flujo de trámites"
)
public class EstadoSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único del estado de la solicitud",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idEstadoSolicitud;

    @NotBlank(message = "El nombre del estado es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(nullable = false, unique = true)
    @Schema(
        description = "Nombre del estado de la solicitud",
        example = "PENDIENTE"
    )
    private String nombre;

    @Size(max = 255)
    @Schema(
        description = "Descripción detallada del estado de la solicitud",
        example = "Solicitud pendiente de revisión por la unidad académica"
    )
    private String descripcion;

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}