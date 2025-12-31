package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleLibroResponse {

    private Long idDetalleLibro;

    private Long idLibro;

    private Integer stockTotal;

    private Integer stockDisponible;

    private String ubicacionFisica;

    private LocalDateTime fechaActualizacion;
}