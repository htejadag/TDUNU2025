package tdunu.MsTitulacion.model.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data <-- No se por que esto me da error
@Entity
@Getter
@Setter
@Table(name = "dictamen")
@NoArgsConstructor
@AllArgsConstructor
public class Dictamen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dictamen")
    private int idDictamen;
    @Column(name = "id_tesis_borrador") private int idTesisBorrador;
    @Column(name = "fecha_hora") private LocalDateTime fechaHora;
    @Column(name = "aula_lugar") private String aulaLugar;
    @Column(name = "modalidad_cat") private int modalidadCategoria;
    @Column(name = "resultado_cat") private int resultadoCategoria;
    @Column(name = "nota_final") private Double notaFinal;

}
