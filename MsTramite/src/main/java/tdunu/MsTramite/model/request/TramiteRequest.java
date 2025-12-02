package tdunu.MsTramite.model.request;
import java.util.Date;

import lombok.Data;

@Data
public class TramiteRequest {

    private Integer modalidad_id;

    private boolean estado = true;

    private Date fecha_inicio;
    
    private Date fecha_fin;

    private String observaciones;
    
}
