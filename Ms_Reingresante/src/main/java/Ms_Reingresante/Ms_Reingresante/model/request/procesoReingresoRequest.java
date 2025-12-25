package Ms_Reingresante.Ms_Reingresante.model.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class procesoReingresoRequest {
     
         private Integer idEstudiante;

     private String procCodigo;

     private LocalDateTime procFechaInicio;

   
     private LocalDateTime procFechaFin;

  
     private String procEstado;

   
     private String procObservaciones;

    private LocalDateTime procFechaPagoFicha;

  
     private LocalDateTime procFechaEmisionFicha;

    private LocalDateTime procFechaPagoInforme;


    private LocalDateTime procFechaSolicitudInforme;

    private LocalDateTime procFechaEmisionInforme;


    private LocalDateTime procFechaSolicitudReingreso;

     private LocalDateTime procFechaResolucion;

     private LocalDateTime procFechaMatricula;

    
    private String procFaseActual;

    
    private LocalDateTime fechaCreacion;


     private String usuarioCreacion;


    private LocalDateTime fechaModificacion;


     private String usuarioModificacion;

}
