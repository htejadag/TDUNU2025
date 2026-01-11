<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.request;
=======
package TDUNU2025.Msbiblioteca.model.request;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class DetalleUsuarioRequest {
    private Long idUsuario;
    private Long idDetalleUsuario;
    private Integer totalPrestamos;
    private Integer totalMultas;
    private LocalDate fechaUltimoPrestamo;
}