package tdunu.MsAsistencia.model.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JustificacionResponse {

    private Integer id;
    private Integer detalleAsistenciaId;
    private String motivo;
    private String tipoDocumento;
    private String numeroDocumento;
    private String archivoAdjunto;
    private LocalDate fechaJustificacion;
    private String aprobado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
}
