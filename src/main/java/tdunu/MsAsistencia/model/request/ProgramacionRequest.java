package tdunu.MsAsistencia.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProgramacionRequest {

    private String sistemaOrigen;
    private String tipoProgramacion;
    private String nombre;
    private String descripcion;
    private Integer contextoId;
    private String tipoContexto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer totalSesiones;
    private String estado;
    private Integer usuarioCreacion;
}
