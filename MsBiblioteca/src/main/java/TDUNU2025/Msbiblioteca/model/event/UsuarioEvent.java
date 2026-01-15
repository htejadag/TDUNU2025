package TDUNU2025.Msbiblioteca.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEvent {

    private String tipoEvento;
    
    private Integer idUsuario;
    private String nombre;
    private String correo;

    private Integer estado; 
}