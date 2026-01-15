package tdunu2025.msbiblioteca.model.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoEvent {

    private String tipoEvento;

    private Long idPrestamo;
    private Long idUsuario;
    private Long idLibro;

    private LocalDateTime fechaEvento;

    private String mensaje;

}
