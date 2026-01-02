package tdunu.MsRevision.model.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RevisionRequest {
    
    private Integer idProyecto;
    private Integer idRevisorUsuario;
    private Integer idTipoFaseCat;      // ID del Catálogo (Ej: Fase Proyecto o Borrador)
    private String comentarios;
    private Integer idEstadoDictamenCat; // ID del Catálogo (Ej: Aprobado, Observado)
    private Integer intentoNumero;
    private LocalDateTime fechaLimiteAtencion;
}