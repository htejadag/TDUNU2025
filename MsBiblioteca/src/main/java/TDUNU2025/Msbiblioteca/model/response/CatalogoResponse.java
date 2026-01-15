package tdunu2025.msbiblioteca.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CatalogoResponse implements Serializable {
    private Long idCatalogo;
    private String nombre;  
    private String descripcion;
    private Integer estado;
    
}