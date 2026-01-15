package tdunu2025.msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

@Data
public class PrestamoRequest {
    
    @NotNull(message="El usuario es obligatorio")
    private Long idUsuario;
    
    @NotNull(message = "el libro (detalle) es obligatorio")
    private Long idDetalleLibro;

    private LocalDate fechaVencimiento;
    
    private Long idEstadoPrestamo;
    private String observaciones;
}