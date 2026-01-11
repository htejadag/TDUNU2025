<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.response;
=======
package TDUNU2025.Msbiblioteca.model.response;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class DetalleUsuarioResponse {
    private Long idUsuario;
    private Long idDetalleUsuario;
    private Integer totalPrestamos;
    private Integer totalMultas;
    private LocalDate fechaUltimoPrestamo;
}