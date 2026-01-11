package TDUNU2025.Msbiblioteca.model.request;

import lombok.Data;

@Data
public class EditorialRequest {

    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private String fechaPago; // Se convierte en LocalDate en el service
    private String pais;
}
