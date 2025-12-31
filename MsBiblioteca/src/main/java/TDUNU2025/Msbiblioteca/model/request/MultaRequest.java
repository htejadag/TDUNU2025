package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultaRequest {

    private Integer idUsuario;

    private Long idPrestamo; 

    private Double monto;

    private String concepto; 

    private Integer idEstadoMulta; 
}