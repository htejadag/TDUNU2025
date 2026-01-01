package tdunu.MsTitulacion.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "acta_aprobacion")
public class ActaAprobacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acta")
    private Integer idActa;

    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "codigo_unico_acta", unique = true, length = 50)
    private String codigoUnicoActa;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "ruta_pdf_firmado", length = 255)
    private String rutaPdfFirmado;
}