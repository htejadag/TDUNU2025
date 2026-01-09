package msposgrado.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "revision")
@Schema(
    description = "Entidad que representa una revisión académica realizada por un jurado sobre una tesis"
)
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único de la revisión",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idRevision;

    @ManyToOne
    @JoinColumn(name = "id_tesis")
    @JsonBackReference(value = "tesis-revision")
    @Schema(
        description = "Tesis asociada a la revisión"
    )
    private Tesis tesis;

    @ManyToOne
    @JoinColumn(name = "id_revisor", nullable = false)
    @Schema(
        description = "Jurado responsable de realizar la revisión"
    )
    private Jurado jurado; // EL JURADO REVISA SEGÚN TU MODELO

    @Schema(
        description = "Tipo de revisión realizada",
        example = "PROYECTO"
    )
    private String tipoRevision;

    @Schema(
        description = "Comentario u observaciones realizadas por el jurado",
        example = "El proyecto cumple con los objetivos planteados"
    )
    private String comentario;

    @Schema(
        description = "Dictamen emitido por el jurado",
        example = "APROBADO"
    )
    private String dictamen;

    @Schema(
        description = "Fecha y hora en que se realizó la revisión",
        example = "2025-06-15T10:30:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime fechaRevision = LocalDateTime.now();

    public Integer getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public Tesis getTesis() {
        return tesis;
    }

    public void setTesis(Tesis tesis) {
        this.tesis = tesis;
    }

    public Jurado getJurado() {
        return jurado;
    }

    public void setJurado(Jurado jurado) {
        this.jurado = jurado;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }

    public void setTipoRevision(String tipoRevision) {
        this.tipoRevision = tipoRevision;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDictamen() {
        return dictamen;
    }

    public void setDictamen(String dictamen) {
        this.dictamen = dictamen;
    }

    public LocalDateTime getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDateTime fechaRevision) {
        this.fechaRevision = fechaRevision;
    }
}