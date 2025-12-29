package TDUNU2025.Msbiblioteca.model.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PrestamoResponse {
    private Integer idPrestamo; // Aquí SÍ incluimos el ID
    private Integer idUsuario;
    private Integer idLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;
    private Integer idEstadoPrestamo;
    private String observaciones;
}