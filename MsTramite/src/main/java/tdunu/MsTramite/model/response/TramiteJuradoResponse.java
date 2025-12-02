package tdunu.MsTramite.model.response;
import lombok.Data;

@Data
public class TramiteJuradoResponse {
    private Integer rol_id;
    private boolean estado = true;

    //foreingkeys
    private TramiteResponse tramite_id;

}
