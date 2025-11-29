package tdunu.MsAlumno.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "alumno")
public class DemoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idalumno")
  private Integer id;

  @Column(name = "alumno_nombre")
  private String nombres;

  @Column(name = "alumno_apellido")
  private String apellidos;

  @Column(name = "alumno_edad")
  private Integer edad;

   @Column(name = "alumno_codigo")
  private String codigo;


}
