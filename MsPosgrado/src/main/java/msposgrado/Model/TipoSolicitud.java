package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tipo_solicitud")
@Schema(
    description = "Entidad que define los distintos tipos de solicitudes del sistema de posgrado"
)
public class TipoSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único del tipo de solicitud",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idTipoSolicitud;

    @NotBlank(message = "El nombre del tipo es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, unique = true)
    @Schema(
        description = "Nombre del tipo de solicitud",
        example = "DESIGNACION_ASESOR"
    )
    private String nombre;

    @Size(max = 255)
    @Schema(
        description = "Descripción del tipo de solicitud",
        example = "Solicitud para la designación de asesor de tesis"
    )
    private String descripcion;

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
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