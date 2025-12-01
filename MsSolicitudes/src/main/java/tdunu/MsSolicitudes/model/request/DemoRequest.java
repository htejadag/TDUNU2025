package tdunu.MsSolicitudes.model.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DemoRequest {

  private Integer idEstudiante;
    private String procCodigo;
    private LocalDate procFechaInicio;
    private String procEstado; 
    private String procFaseActual; 
    private String procObservaciones;
    private String usuarioCreacion;
}
