package tdunu.MsRevision.model.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RevisionResponse {

    private Integer idRevision;
    private Integer idProyecto;
    private Integer idRevisorUsuario;
    
    // Datos de Fase
    private Integer idTipoFaseCat;
    private String nombreTipoFase;      // Ej: "Fase 1: Proyecto"

    private String comentarios;

    // Datos de Estado/Dictamen
    private Integer idEstadoDictamenCat;
    private String nombreEstadoDictamen; // Ej: "Observado"

    private Integer intentoNumero;
    private LocalDateTime fechaRevision;
    private LocalDateTime fechaLimiteAtencion;
}