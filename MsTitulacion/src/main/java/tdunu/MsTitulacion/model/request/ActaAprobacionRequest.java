package tdunu.MsTitulacion.model.request;

import lombok.Data;

@Data
public class ActaAprobacionRequest {
    private Integer idProyecto;
    private String codigoUnicoActa;
    private String rutaPdfFirmado;
}