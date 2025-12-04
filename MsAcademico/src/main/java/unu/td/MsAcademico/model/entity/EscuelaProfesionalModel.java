package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "escuelaProfesional")
@EntityListeners(AuditingEntityListener.class)
public class EscuelaProfesionalModel extends BaseModel {

    @ManyToOne()
    @JoinColumn(name = "idFacultad", nullable = false)
    private FacultadModel facultad;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer duracionCarrera;

}
