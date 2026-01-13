package tdunu.MsTitulacion.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResolucionTituloResponse {
    
    private int idResolucion;
    private int idDictamen;
    private String numeroResolucion;
    private LocalDateTime fechaEmision;
    private String rutaPdfTitulo;
    private boolean registroPorSunedu;

}

private int idDictamen;
    private int idTesisBorrador;  //FK
    private Date fechaHora;
    private String aulaLugar;
    private int modalidadCategoria;
    private int resultadoCategoria;
    private Double notaFinal;
