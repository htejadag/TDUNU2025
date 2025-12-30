package TDUNU2025.Msbiblioteca.model.request;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleLibroRequest {

    // Clave Foránea del Libro
    @NotNull(message = "El ID del libro es obligatorio")
    @Min(value = 1, message = "El ID del libro debe ser un número positivo")
    @JsonProperty("idLibro")
    private Long idLibro;

    // stockTotal (integer, NN - No Nulo)
    @NotNull(message = "El stock total es obligatorio")
    @Min(value = 0, message = "El stock total no puede ser negativo")
    @JsonProperty("stockTotal")
    private Integer stockTotal;

    // stockDisponible (integer, NN - No Nulo)
    @NotNull(message = "El stock disponible es obligatorio")
    @Min(value = 0, message = "El stock disponible no puede ser negativo")
    @JsonProperty("stockDisponible")
    private Integer stockDisponible;


    @NotBlank(message = "La ubicación física no puede estar vacía")
    @JsonProperty("ubicacionFisica")
    private String ubicacionFisica;  

}
=======
import lombok.Data;

@Data
public class DetalleLibroRequest {

    private Long idLibro;          // FK al libro
    private Integer stockTotal;
    private Integer stockDisponible;
    private String ubicacionFisica;
}
>>>>>>> origin/origin/jordinTrujillo
