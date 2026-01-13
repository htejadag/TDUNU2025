package tdunu.MsTitulacion.model.response;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TesisBorradorResponse {
    
    private Integer idTesisBorrador ;
    private Integer idProyecto;
    private String rutaConstanciaCoti;
    private String rutaBorrador;
    private Integer estadoBorrador;
    private LocalDateTime fechaSubida;

}
