public package tdunu.MsTitulacion.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DictamenResponse {
    private Integer id;
    private String tituloTesis;     // Obtenido del microservicio de Tesis
    private String resultadoFinal;  // Ej: "APROBADO POR UNANIMIDAD" [cite: 536]
    private String observaciones;   // Comentarios finales de la terna [cite: 501]
    private LocalDateTime fechaDictamen;
    private String nombrePresidente; // Nombre del jurado que presidió [cite: 543]
} {
    
}
