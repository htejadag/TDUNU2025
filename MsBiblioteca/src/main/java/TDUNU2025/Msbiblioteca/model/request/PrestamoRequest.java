<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.request;
=======
package TDUNU2025.Msbiblioteca.model.request;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;
import java.time.LocalDate;

@Data
public class PrestamoRequest {
    // No pedimos idPrestamo porque es autogenerado
    private Long idUsuario;
    private Long idDetalleLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion; // Puede ser null
    private Long idEstadoPrestamo;
    private String observaciones;
}