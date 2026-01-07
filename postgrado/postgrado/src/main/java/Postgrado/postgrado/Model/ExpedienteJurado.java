package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonBackReference;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "expediente_jurado")
@Data
public class ExpedienteJurado extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpJurado;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    // @JsonBackReference(value = "exp-jurado") -- Comentado para permitir deser al
    // crear
    @NotNull(message = "El expediente es obligatorio")
    private Expediente expediente;

    @ManyToOne
    @JoinColumn(name = "id_jurado", nullable = false)
    @NotNull(message = "El jurado es obligatorio")
    private Jurado jurado;

    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 50, message = "El rol no puede superar los 50 caracteres")
    private String rol;

    // fechaDesignacion eliminada, se usa auditoría
}
