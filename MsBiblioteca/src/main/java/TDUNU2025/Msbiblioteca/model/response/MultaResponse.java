package tdunu2025.msbiblioteca.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MultaResponse {
    private Long idMulta;
    private Long idUsuario;
    private Long idPrestamo;
    private BigDecimal monto;
    private String concepto;
    private LocalDateTime fechaGeneracion;
    private LocalDate fechaPago;
    private Long idEstadoMulta;
    private Integer diasRetraso;
}