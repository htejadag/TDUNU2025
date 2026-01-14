package tdunu2025.msbiblioteca.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditorialResponse {
    @JsonProperty("idEditorial")
    private Long idEditorial;

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

    @JsonProperty("fechaRegistro")
    private LocalDateTime fechaRegistro;

    @JsonProperty("fechaActualizacion")
    private LocalDateTime fechaActualizacion;
}