package tdunu.MsTramite.model.response;

import java.util.Date;
import lombok.Data;

@Data
public class TramiteAsesorResponse {
    private Date fecha_designacion;
    private boolean estado = true;
    //foreingkeys
    private TramiteResponse tramite_id;
}
