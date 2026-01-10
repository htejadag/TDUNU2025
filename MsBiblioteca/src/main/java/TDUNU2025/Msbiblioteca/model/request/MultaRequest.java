package tdunu2025.msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MultaRequest {
    
    private Long idUsuario;
    private Long idPrestamo;
    private Double monto;
    private String concepto;
    private LocalDate fechaGeneracion; // Se envía si se permite fecha manual, sino se puede omitir y usar LocalDate.now() en el Service
    private LocalDate fechaPago;
    private Long idEstadoMulta;
    
}