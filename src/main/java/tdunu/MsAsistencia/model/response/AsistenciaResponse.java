package tdunu.MsAsistencia.model.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AsistenciaResponse {

    private Integer id;
    private Integer programacionId;
    private Integer numeroSesion;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer sedeId;
    private Integer aulaId;
    private String temaTratado;
    private String estado;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
}
