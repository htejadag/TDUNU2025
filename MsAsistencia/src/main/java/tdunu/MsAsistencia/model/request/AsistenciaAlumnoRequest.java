package tdunu.MsAsistencia.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AsistenciaAlumnoRequest {

    private Integer alumnoId;

    private Integer cursoId;

    private Integer seccionId;

    private LocalDate fecha;

    private LocalDateTime horaEntrada;

    private LocalDateTime horaSalida;

    private String estado;

    private String justificacion;

    private String observaciones;

    private String usuarioRegistro;
}
