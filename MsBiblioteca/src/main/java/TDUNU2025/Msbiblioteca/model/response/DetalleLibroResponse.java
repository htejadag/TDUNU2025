<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.response;
=======
package TDUNU2025.Msbiblioteca.model.response;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleLibroResponse {

    @JsonProperty("idDetalleLibro")
    private Long idDetalleLibro;

    @JsonProperty("idLibro") 
    private Long idLibro; 
    
    @JsonProperty("stockTotal")
    private Integer stockTotal;

    @JsonProperty("stockDisponible")
    private Integer stockDisponible;

    @JsonProperty("ubicacionFisica")
    private String ubicacionFisica;

    @JsonProperty("fechaActualizacion")
    private LocalDateTime fechaActualizacion;

}
