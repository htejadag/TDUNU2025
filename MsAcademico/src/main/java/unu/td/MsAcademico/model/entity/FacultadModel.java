package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "facultades")
//@EntityListeners(AuditingEntityListener.class)
public class FacultadModel extends EntidadAcademicaBaseModel {

}
