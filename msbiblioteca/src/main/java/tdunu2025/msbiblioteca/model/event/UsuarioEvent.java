package tdunu2025.msbiblioteca.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEvent {

    private String tipoEvento;

    private long idUsuario;
    private String nombre;
    private String correo;
    
    private long idEstadousuario;

}
