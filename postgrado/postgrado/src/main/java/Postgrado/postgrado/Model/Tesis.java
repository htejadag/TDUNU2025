package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tesis")
@Data
public class Tesis extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTesis;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-tesis")
    @NotNull(message = "El expediente es obligatorio")
    private Expediente expediente;

    @OneToMany(mappedBy = "tesis")
    @JsonManagedReference(value = "tesis-revision")
    private List<Revision> revisiones;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String proyectoPdf;
    private String informeFinalPdf;
    private String antiplagioProyectoPdf;
    private String antiplagioFinalPdf;

    private String estadoProyecto;
    private String estadoInformeFinal;

    // fechaRegistro eliminada, se usa auditoría
}
