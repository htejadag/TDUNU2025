package tdunu.MsTitulacion.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TesisBorradorResponse {
    
    private int idTesisBorrador ;
    private int idProyecto;
    private String rutaConstanciaCoti;
    private String rutaBorrador;
    private int estadoBorrador;
    private LocalDateTime fechaSubida;

}
