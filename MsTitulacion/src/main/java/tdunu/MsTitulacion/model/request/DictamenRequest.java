package tdunu.MsTitulacion.model.request;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DictamenRequest {
        
    private Integer idDictamen;
    private int idTesisBorrador;
    private LocalDateTime fechaHora;
    private String aulaLugar;
    private Integer modalidadCategoria;
    private Integer resultadoCategoria;
    private Double notaFinal;
    
    
}
