package tdunu.MsCatalogo.model.response;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TipoCatalogoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}
