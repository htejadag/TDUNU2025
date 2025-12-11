package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "facultades")
@EntityListeners(AuditingEntityListener.class)
public class FacultadModel extends EntidadAcademicaBaseModel {

}
