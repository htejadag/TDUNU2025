package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "escuelasProfesionales")
@EqualsAndHashCode(callSuper=false)
public class EscuelaProfesionalModel extends EntidadAcademicaBaseModel {

    @ManyToOne()
    @JoinColumn(name = "idFacultad", nullable = false)
    private FacultadModel facultad;

    @Column(nullable = false)
    private Integer duracionCarrera;

}
