<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.response;
=======
package TDUNU2025.Msbiblioteca.model.response;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class PrestamoResponse {
    private Long idPrestamo; 
    private Long idUsuario;
    private Long idDetalleLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;
    private Long idEstadoPrestamo;
    private String observaciones;
}