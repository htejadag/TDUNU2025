package tdunu.MsTramite.model.request;
import java.math.BigInteger;

import lombok.Data;

@Data
public class TramiteJuradoRequest {
 
    private Integer rol_id;

    private boolean estado = true;

    //foreingkeys
    private BigInteger tramite_id;


}
