package tdunu.MsAsistencia.model.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProgramacionResponse {

    private Integer id;
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
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
}
