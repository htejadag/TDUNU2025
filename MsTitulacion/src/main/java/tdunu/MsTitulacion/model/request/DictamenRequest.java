package tdunu.MsTitulacion.model.request;

import lombok.Data;
import java.util.List;

@Data
public class DictamenRequest {

    // Referencia al borrador que se está calificando [cite: 872]
    private Integer idBorrador;

    // ID del evento de sustentación del otro grupo (para vinculación)
    private Long sustentacionIdExterno;

    // Resultado: APROBADO_UNANIMIDAD, DESAPROBADO, OBSERVADO [cite: 503]
    private String resultado;

    // Comentarios finales de la terna de jurados [cite: 542]
    private String observaciones;
    
    // Lista de IDs de los jurados que firman el acta [cite: 544]
    private List<Integer> idsJurados;
}