package TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DetalleUsuarioRequest {
    private Long idUsuario;
    private Long idDetalleUsuario;
    private Integer totalPrestamos;
    private Integer totalMultas;
    private LocalDate fechaUltimoPrestamo;
}