<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.response;
=======
package TDUNU2025.Msbiblioteca.model.response;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class MultaResponse {
    private Long idMulta;
    private Long idUsuario;
    private Long idPrestamo;
    private Double monto;
    private String concepto;
    private LocalDate fechaGeneracion;
    private LocalDate fechaPago;
    private Long idEstadoMulta;
    private Integer diasRetraso;
}