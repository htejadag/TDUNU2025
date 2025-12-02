package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "asistencia_alumno")
public class AsistenciaAlumnoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "alumno_id")
    private Integer alumnoId;

    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "seccion_id")
    private Integer seccionId;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_entrada")
    private LocalDateTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "justificacion", length = 500)
    private String justificacion;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "usuario_registro", length = 100)
    private String usuarioRegistro;
}
