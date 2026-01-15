package tdunu2025.msbiblioteca.model.request;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Data
public class MultaRequest {
    
    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "el prestamo asociado es obligatorio")
    private Long idPrestamo;

    @NotNull(message = "el monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "el monto debe ser mayor que 0")
    private BigDecimal monto;
    
    private String concepto;
    
    private Long idEstadoMulta;
    
}