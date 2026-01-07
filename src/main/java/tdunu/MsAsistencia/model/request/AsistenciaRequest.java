package tdunu.MsAsistencia.model.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AsistenciaRequest {

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
    private Integer usuarioCreacion;
}
