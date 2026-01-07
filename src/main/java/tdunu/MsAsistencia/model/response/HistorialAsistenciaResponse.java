package tdunu.MsAsistencia.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistorialAsistenciaResponse {

    private Integer id;
    private Integer detalleAsistenciaId;
    private String estadoAnterior;
    private String estadoNuevo;
    private String motivoCambio;
    private LocalDateTime fechaCreacion;
    private Integer usuarioCreacion;
}
