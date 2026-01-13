package tdunu.MsTitulacion.model.request;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DictamenRequest {
        
    private int idDictamen;
    private int idTesisBorrador;
    private LocalDateTime fechaHora;
    private String aulaLugar;
    private int modalidadCategoria;
    private int resultadoCategoria;
    private Double notaFinal;
    
    
}
