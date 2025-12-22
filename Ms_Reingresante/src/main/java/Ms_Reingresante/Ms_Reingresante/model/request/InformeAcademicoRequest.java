package Ms_Reingresante.Ms_Reingresante.model.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data

public class InformeAcademicoRequest {

     private Integer idProceso;
    private String infNumero;
    private String infTupa;
    private String infNumRegistro;

    // ✅ TIME
    private LocalTime horaRecepcion;

    // ✅ DATE
    private LocalDate fechaSolicitud;

    // ✅ DATE
    private LocalDate fechaEmision;

    private String emitidoPor;

    // ✅ DATETIME
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;

}