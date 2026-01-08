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

    @NotBlank(message = "El tipo de solicitud es obligatorio")
    @Size(max = 50, message = "El tipo de solicitud no puede superar los 50 caracteres")
    private String tipoSolicitud;

    @Size(max = 20, message = "El estado no puede cerrar los 20 caracteres")
    private String estadoSolicitud;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "solicitud")
    @JsonManagedReference(value = "sol-doc")
    private List<Documento> documentos;

    @PrePersist
    public void prePersist() {
        if (this.estadoSolicitud == null) {
            this.estadoSolicitud = "PENDIENTE";
        }
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

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
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
