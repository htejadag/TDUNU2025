package tdunu.MsAsistencia.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class JustificacionRequest {

    private Integer detalleAsistenciaId;
    private String motivo;
    private String tipoDocumento;
    private String numeroDocumento;
    private String archivoAdjunto;
    private LocalDate fechaJustificacion;
    private Integer usuarioCreacion;
}
