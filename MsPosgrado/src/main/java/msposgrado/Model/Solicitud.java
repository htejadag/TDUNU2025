package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Entidad que representa una solicitud realizada dentro de un expediente académico.
 * Forma parte del flujo del trámite de posgrado.
 */
@Entity
@Table(name = "solicitudes")
@Schema(
    description = "Entidad que representa una solicitud asociada a un expediente académico"
)
public class Solicitud extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único de la solicitud",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    @NotNull(message = "El expediente es obligatorio")
    @Schema(
        description = "Expediente al cual pertenece la solicitud"
    )
    private Expediente expediente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_solicitud", nullable = false)
    @NotNull(message = "El tipo de solicitud es obligatorio")
    @Schema(
        description = "Tipo de solicitud realizada",
        example = "DESIGNACION_ASESOR"
    )
    private TipoSolicitud tipoSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_estado_solicitud", nullable = false)
    @NotNull(message = "El estado de la solicitud es obligatorio")
    @Schema(
        description = "Estado actual de la solicitud",
        example = "PENDIENTE"
    )
    private EstadoSolicitud estadoSolicitud;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Schema(
        description = "Descripción u observaciones adicionales de la solicitud",
        example = "Solicitud generada automáticamente al registrar expediente"
    )
    private String descripcion;

    @OneToMany(mappedBy = "solicitud")
    @JsonManagedReference(value = "sol-doc")
    @Schema(
        description = "Lista de documentos asociados a la solicitud"
    )
    private List<Documento> documentos;

    /**
     * Método ejecutado antes de persistir la entidad.
     * La asignación del estado por defecto debe manejarse desde la lógica
     * del servicio o enviarse explícitamente en la solicitud.
     */
    @PrePersist
    public void prePersist() {
        // La lógica de estado por defecto debe manejarse asegurando que se envíe el ID
        // del estado PENDIENTE
        // o asignándolo aquí buscando la entidad, pero por simplicidad requeriremos que
        // se envíe.
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}