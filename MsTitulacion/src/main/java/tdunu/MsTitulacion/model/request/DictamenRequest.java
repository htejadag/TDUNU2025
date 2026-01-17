package tdunu.MsTitulacion.model.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DictamenRequest {
        
    private int idTesisBorrador;
    private LocalDateTime fechaHora;
    private String aulaLugar;
    private int modalidadCategoria;
    private int resultadoCategoria;
    private Double notaFinal;
    
    
}
