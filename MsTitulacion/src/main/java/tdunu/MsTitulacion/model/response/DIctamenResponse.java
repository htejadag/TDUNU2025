package tdunu.MsTitulacion.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class DictamenResponse {
    
    private Integer idDictamen;
    private Integer idTesisBorrador;  //FK
    private Date fechaHora;
    private String aulaLugar;
    private Integer modalidadCategoria;
    private Integer resultadoCategoria;
    private Double notaFinal;

}
