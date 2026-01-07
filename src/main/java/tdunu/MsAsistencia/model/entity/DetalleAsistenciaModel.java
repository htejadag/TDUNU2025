package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "detalle_asistencia")
public class DetalleAsistenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "asistencia_id", nullable = false)
    private Integer asistenciaId;

    @Column(name = "entidad_id", nullable = false)
    private Integer entidadId;

    @Column(name = "tipo_entidad", length = 50, nullable = false)
    private String tipoEntidad;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuarioCreacion", nullable = false)
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    // Relación con Asistencia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asistencia_id", insertable = false, updatable = false)
    private AsistenciaModel asistencia;
}
