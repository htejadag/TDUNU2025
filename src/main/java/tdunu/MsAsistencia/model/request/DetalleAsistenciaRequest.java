package tdunu.MsAsistencia.model.request;

import lombok.Data;
import java.util.List;

@Data
public class DetalleAsistenciaRequest {

    private Integer asistenciaId;
    private Integer entidadId;
    private String tipoEntidad;
    private String estado;
    private String observaciones;
    private Integer usuarioCreacion;
}
