package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleUsuarioRequest {

    private Integer idUsuario;

    private Integer idDetalleUsuario;
    
    private Integer totalPrestamos;
    
    private Integer totalMultas;
    
    private LocalDate fechaUltimoPrestamo;
}