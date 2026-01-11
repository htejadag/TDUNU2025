package Ms_Reingresante.Ms_Reingresante.model.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data

public class InformeAcademicoResponse {

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
    private LocalDate fechaCreacion;
    private String usuarioCreacion;
    private LocalDate fechaModificacion;
    private String usuarioModificacion;

} 