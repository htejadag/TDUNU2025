package tdunu.MsTramite.model.request;
import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class TramiteMovimientoRequest {
 
    private Date fecha_envio;

    private Date fecha_recepcion;

    private boolean estado_tramite_id = true;

    //foreingkeys
    private BigInteger tramite_id;

}
