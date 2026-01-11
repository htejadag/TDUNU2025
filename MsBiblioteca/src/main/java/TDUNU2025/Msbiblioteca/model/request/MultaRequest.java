package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultaRequest {

    private Integer idUsuario;

    private Integer idPrestamo; 
    
    private Double monto;

    private String concepto; 

    private Integer idEstadoMulta; 
}