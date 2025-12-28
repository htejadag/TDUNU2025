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
    private Integer id;

    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "codigo_unico_acta", unique = true)
    private String codigoActa; // Documento oficial válido [cite: 532, 873]

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "ruta_pdf_firmado")
    private String rutaPdfFirmado;
}