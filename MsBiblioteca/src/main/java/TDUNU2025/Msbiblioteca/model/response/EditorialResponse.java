package TDUNU2025.Msbiblioteca.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditorialResponse {

    private Long idEditorial;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private String fechaPago;
    private String pais;
}
