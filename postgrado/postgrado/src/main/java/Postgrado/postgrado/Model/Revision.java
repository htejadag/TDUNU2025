package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonBackReference;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "revision")
@Data
public class Revision extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRevision;

    @ManyToOne
    @JoinColumn(name = "id_tesis")
    @JsonBackReference(value = "tesis-revision")
    @NotNull(message = "La tesis es obligatoria")
    private Tesis tesis;

    @ManyToOne
    @JoinColumn(name = "id_revisor", nullable = false)
    @NotNull(message = "El jurado es obligatorio")
    private Jurado jurado; // EL JURADO REVISA SEGÚN TÚ MODELO

    @NotBlank(message = "El tipo de revisión es obligatorio")
    private String tipoRevision;

    private String comentario;

    @NotBlank(message = "El dictamen es obligatorio")
    private String dictamen;

    // fechaRevision eliminada, se usa auditoría
}
