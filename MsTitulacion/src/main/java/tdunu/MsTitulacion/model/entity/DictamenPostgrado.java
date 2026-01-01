package tdunu.MsTitulacion.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "dictamen_postgrado")
public class DictamenPostgrado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acto")
    private Integer idActo;

    @Column(name = "id_borrador")
    private Integer idBorrador;

    @Column(name = "fecha_programada")
    private LocalDate fechaProgramada;

    @Column(name = "hora_programada")
    private LocalTime horaProgramada;

    @Column(name = "aula_virtual_fisica", length = 150)
    private String aulaVirtualFisica;
  
    @Column(name = "id_modalidad_cat")
    private Integer idModalidadCat;

    @Column(name = "id_resultado_final_cat")
    private Integer idResultadoFinalCat;
}