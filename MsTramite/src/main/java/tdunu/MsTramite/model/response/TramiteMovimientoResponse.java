package tdunu.MsTramite.model.response;

import java.util.Date;
import lombok.Data;

@Data
public class TramiteMovimientoResponse {
    private Date fecha_envio;
    private Date fecha_recepcion;
    private boolean estado_tramite_id = true;
    //foreingkeys
    private TramiteResponse tramite_id;

}
