package tdunu.MsTitulacion.model.response;

import java.sql.Date;

public class DictamenResponse {
    private int idDictamen;
    private int idTesisBorrador;  //FK
    private Date fechaHora;
    private String aulaLugar;
    private int modalidadCategoria;
    private int resultadoCategoria;
    private Double notaFinal;
}
