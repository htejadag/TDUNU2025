package tdunu.MsAsistencia.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DetalleAsistenciaResponse {

    private Integer id;
    private Integer asistenciaId;
    private Integer entidadId;
    private String tipoEntidad;
    private String estado;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
}
