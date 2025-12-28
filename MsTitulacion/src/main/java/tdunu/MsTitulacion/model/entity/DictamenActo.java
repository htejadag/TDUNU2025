package tdunu.MsTitulacion.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dictamen_postgrado") // Nombre específico para no chocar
public class DictamenActo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_borrador", nullable = false)
    private Integer idBorrador; // Tu conexión con el flujo de Tesis 

    // Este ID vincula tu dictamen con el "evento" que el otro grupo programó
    @Column(name = "sustentacion_id_externo")
    private Long sustentacionIdExterno; 

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado_unanimidad")
    private ResultadoPostgrado resultado; // Requisito Manual: Unanimidad [cite: 536]

    @Column(name = "observaciones_finales", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "fecha_dictamen")
    private LocalDateTime fechaDictamen;
}

enum ResultadoPostgrado {
    APROBADO_UNANIMIDAD, // Único estado que permite el acta en Postgrado [cite: 503]
    DESAPROBADO, 
    OBSERVADO
} 