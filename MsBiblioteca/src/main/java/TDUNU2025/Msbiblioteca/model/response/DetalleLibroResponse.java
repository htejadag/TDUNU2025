package tdunu2025.msbiblioteca.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleLibroResponse {

    private Long idDetalleLibro;
    private Long idLibro;     
    private Integer stockTotal;
    private Integer stockDisponible;
    private String ubicacionFisica;
    private LocalDateTime fechaActualizacion;

}
