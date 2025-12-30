package TDUNU2025.Msbiblioteca.model.response;

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/origin/jordinTrujillo
}
