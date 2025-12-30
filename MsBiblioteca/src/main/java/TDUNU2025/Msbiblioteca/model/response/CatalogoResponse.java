package TDUNU2025.Msbiblioteca.model.response;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogoResponse implements Serializable {
    private Integer idCatalogo;
    private String nombre;  
    private String descripcion;
    private Integer estado;
    
}