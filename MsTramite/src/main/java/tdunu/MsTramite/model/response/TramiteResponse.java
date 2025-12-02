package tdunu.MsTramite.model.response;

import java.util.Date;
import lombok.Data;

@Data
public class TramiteResponse {
    
    private Integer modalidad_id;
    private boolean estado = true;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String observaciones;

}
