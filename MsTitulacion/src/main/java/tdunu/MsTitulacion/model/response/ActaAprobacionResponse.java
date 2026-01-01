package tdunu.MsTitulacion.model.response;

import lombok.Data;

@Data
public class ActaAprobacionResponse {
    private Integer idActa;
    private String codigoUnicoActa;  
    private String fechaEmisionFormateada; 
    private String rutaDescargaPdf;  
    
    // Datos extras que suelen pedirse al visualizar el acta
    private String tituloProyecto; // Opcional, dependiendo de si cruzas info
    private String escuelaProfesional; 
}