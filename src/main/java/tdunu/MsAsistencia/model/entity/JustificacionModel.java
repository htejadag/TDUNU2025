package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "justificacion")
public class JustificacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "detalle_asistencia_id", nullable = false)
    private Integer detalleAsistenciaId;

    @Column(name = "motivo", length = 500, nullable = false)
    private String motivo;

    @Column(name = "tipo_documento", length = 50)
    private String tipoDocumento;

    @Column(name = "numero_documento", length = 100)
    private String numeroDocumento;

    @Column(name = "archivo_adjunto", length = 500)
    private String archivoAdjunto;

    @Column(name = "fecha_justificacion", nullable = false)
    private LocalDate fechaJustificacion;

    @Column(name = "aprobado", length = 10, nullable = false)
    private String aprobado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuarioCreacion", nullable = false)
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    // Relación con DetalleAsistencia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalle_asistencia_id", insertable = false, updatable = false)
    private DetalleAsistenciaModel detalleAsistencia;
}
