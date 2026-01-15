package tdunu2025.msbiblioteca.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PrestamoResponse {
    
    private Long idPrestamo; 
    private Long idUsuario;
    private Long idDetalleLibro;
    private String tituloLibro;
    private LocalDateTime fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDateTime fechaDevolucion;
    private Long idEstadoPrestamo;
    private String observaciones;
}