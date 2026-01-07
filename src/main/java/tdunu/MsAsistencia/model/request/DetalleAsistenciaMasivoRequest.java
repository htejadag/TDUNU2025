package tdunu.MsAsistencia.model.request;

import lombok.Data;
import java.util.List;

@Data
public class DetalleAsistenciaMasivoRequest {

    private Integer asistenciaId;
    private List<DetalleIndividualRequest> detalles;
    private Integer usuarioCreacion;

    @Data
    public static class DetalleIndividualRequest {
        private Integer entidadId;
        private String tipoEntidad;
        private String estado;
        private String observaciones;
    }
}
