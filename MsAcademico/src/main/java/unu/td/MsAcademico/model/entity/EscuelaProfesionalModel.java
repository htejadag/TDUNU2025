package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "escuelasProfesionales")
@EntityListeners(AuditingEntityListener.class)
public class EscuelaProfesionalModel extends EntidadAcademicaBaseModel {

    @ManyToOne()
    @JoinColumn(name = "idFacultad", nullable = false)
    private FacultadModel facultad;

    @Column(nullable = false)
    private Integer duracionCarrera;

}
