package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "documentos")
@Schema(
    description = "Entidad que representa un documento asociado a una solicitud dentro del sistema"
)
public class Documento extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único del documento",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idDocumento;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    @JsonBackReference(value = "sol-doc")
    @NotNull(message = "La solicitud es obligatoria")
    @Schema(
        description = "Solicitud a la que pertenece el documento"
    )
    private Solicitud solicitud;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Size(max = 50, message = "El tipo de documento no puede superar los 50 caracteres")
    @Schema(
        description = "Tipo de documento generado",
        example = "DICTAMEN_JURADO"
    )
    private String tipoDocumento;

    @NotBlank(message = "La ruta del archivo es obligatoria")
    @Size(max = 255, message = "La ruta del archivo no puede superar los 255 caracteres")
    @Schema(
        description = "Ruta donde se encuentra almacenado el archivo del documento",
        example = "documentos/dictamen_12.pdf"
    )
    private String archivoRuta;

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }
}