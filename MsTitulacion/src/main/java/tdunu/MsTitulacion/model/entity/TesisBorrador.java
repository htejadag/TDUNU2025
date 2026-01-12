package tdunu.MsTitulacion.model.entity;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Data <-- No se por que esto me da error
@Entity
@Getter
@Setter
@Table(name = "tesis_borrador")
public class TesisBorrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tesis_borrador")
    private int idTesisBorrador ;

    @Column(name = "id_proyecto")private int idProyecto;
    @Column(name = "ruta_constancia_coti")private String rutaConstanciaCoti;
    @Column(name = "ruta_borrador")private String rutaBorrador;
    @Column(name = "estado_borrador")private int estadoBorrador;
    @Column(name = "fecha_subida")private LocalDateTime fechaSubida;

}
