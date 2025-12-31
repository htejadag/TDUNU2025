package TDUNU2025.Msbiblioteca.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditorialRequest {

    private String nombre;

    private String direccion;

    private String telefono;

    private String email;

    private String sitioWeb;

    private String pais;
}