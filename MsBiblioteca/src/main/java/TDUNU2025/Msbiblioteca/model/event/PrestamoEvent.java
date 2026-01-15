package TDUNU2025.Msbiblioteca.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoEvent {

    private String tipoEvento;
    
    private Integer idPrestamo;
    private Integer idUsuario;
    private Integer idLibro;

    private LocalDateTime fechaEvento;

    private String mensaje;
}