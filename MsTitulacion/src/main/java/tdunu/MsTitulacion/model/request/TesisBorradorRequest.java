package tdunu.MsTitulacion.model.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TesisBorradorRequest {
    
    private Integer idTesisBorrador ;
    private Integer idProyecto;
    private String rutaConstanciaCoti;
    private String rutaBorrador;
    private Integer estadoBorrador;
    private LocalDateTime fechaSubida;
    
}
