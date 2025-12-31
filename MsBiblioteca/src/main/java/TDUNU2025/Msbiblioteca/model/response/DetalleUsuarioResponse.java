package TDUNU2025.Msbiblioteca.model.response;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleUsuarioResponse {

    private Integer idDetalleUsuario;

    private Integer idUsuario;

    private Integer totalPrestamos;

    private Integer totalMultas;

    private LocalDate fechaUltimoPrestamo;

    private LocalDateTime fechaActualizacion;
}