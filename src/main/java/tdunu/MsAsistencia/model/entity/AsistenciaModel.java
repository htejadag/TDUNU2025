package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "asistencia")
public class AsistenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "programacion_id", nullable = false)
    private Integer programacionId;

    @Column(name = "numero_sesion")
    private Integer numeroSesion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "sede_id")
    private Integer sedeId;

    @Column(name = "aula_id")
    private Integer aulaId;

    @Column(name = "tema_tratado", length = 500)
    private String temaTratado;

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

    // Relación con Programacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programacion_id", insertable = false, updatable = false)
    private ProgramacionModel programacion;
}
