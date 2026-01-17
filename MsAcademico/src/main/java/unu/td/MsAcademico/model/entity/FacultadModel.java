package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "facultades")
@EqualsAndHashCode(callSuper=false)
public class FacultadModel extends EntidadAcademicaBaseModel {

}
