package Ms_Reingresante.Ms_Reingresante.model.response;


import java.time.LocalDate;
import lombok.Data;

@Data

public class ProcesoReingresoResponse {
 
      private Integer idEstudiante;

     private String procCodigo;

     private LocalDate procFechaInicio;

   
     private LocalDate procFechaFin;

  
     private String procEstado;

   
     private String procObservaciones;

    private LocalDate procFechaPagoFicha;

  
     private LocalDate procFechaEmisionFicha;

    private LocalDate procFechaPagoInforme;


    private LocalDate procFechaSolicitudInforme;

    private LocalDate procFechaEmisionInforme;


    private LocalDate procFechaSolicitudReingreso;

     private LocalDate procFechaResolucion;

     private LocalDate procFechaMatricula;

    
    private String procFaseActual;

    
    private LocalDate fechaCreacion;


     private String usuarioCreacion;


    private LocalDate fechaModificacion;


     private String usuarioModificacion;

}
