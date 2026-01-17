package tdunu.MsTitulacion.model.request;

import lombok.Data;


@Data
public class TesisBorradorRequest {
    
    private int idProyecto;
    private String rutaConstanciaCoti;
    private String rutaBorrador;
    private int estadoBorrador;
    
}
