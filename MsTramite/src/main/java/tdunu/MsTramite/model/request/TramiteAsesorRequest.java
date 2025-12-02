package tdunu.MsTramite.model.request;
import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class TramiteAsesorRequest {
    
    private Date fecha_designacion;

    private boolean estado = true;

    //foreingkeys
    private BigInteger tramite_id;

}
