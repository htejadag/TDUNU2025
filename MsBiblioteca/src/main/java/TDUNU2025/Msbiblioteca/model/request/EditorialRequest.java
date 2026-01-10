package tdunu2025.msbiblioteca.model.request;

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
    private String pais;
}
