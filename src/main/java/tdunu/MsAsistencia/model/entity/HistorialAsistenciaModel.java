package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial_asistencia")
public class HistorialAsistenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "detalle_asistencia_id", nullable = false)
    private Integer detalleAsistenciaId;

    @Column(name = "estado_anterior", length = 20, nullable = false)
    private String estadoAnterior;

    @Column(name = "estado_nuevo", length = 20, nullable = false)
    private String estadoNuevo;

    @Column(name = "motivo_cambio", length = 500)
    private String motivoCambio;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuarioCreacion", nullable = false)
    private Integer usuarioCreacion;

    // Relación con DetalleAsistencia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalle_asistencia_id", insertable = false, updatable = false)
    private DetalleAsistenciaModel detalleAsistencia;
}
