package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "solicitudes")
public class Solicitud extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    @NotNull(message = "El expediente es obligatorio")
    private Expediente expediente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_solicitud", nullable = false)
    @NotNull(message = "El tipo de solicitud es obligatorio")
    private TipoSolicitud tipoSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_estado_solicitud", nullable = false)
    @NotNull(message = "El estado de la solicitud es obligatorio")
    private EstadoSolicitud estadoSolicitud;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "solicitud")
    @JsonManagedReference(value = "sol-doc")
    private List<Documento> documentos;

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
