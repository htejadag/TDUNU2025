package TDUNU2025.Msbiblioteca.model.request;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditorialRequest {

    @NotBlank(message = "El nombre es obligatorio") 
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("direccion")
    private String direccion; 

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("email")
    private String email;

    @JsonProperty("sitioWeb")
    private String sitioWeb;

    @JsonProperty("pais")
=======
import lombok.Data;

@Data
public class EditorialRequest {

    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String sitioWeb;
    private String fechaPago; // Se convierte en LocalDate en el service
>>>>>>> origin/origin/jordinTrujillo
    private String pais;
}
