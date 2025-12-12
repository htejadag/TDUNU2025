package Ms_Reingresante.Ms_Reingresante.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InformeAcademicoResponse {

    // Clave Foránea: Necesario para vincular el informe al proceso
    public Integer idProceso;

    // Datos principales del registro (según el diagrama DB)
    public String infNumero;
    public String infTupa;
    public String infNumRegistro;
    public LocalTime horaRecepcion; // Se necesita LocalTime para TIME
    public LocalDate fechaSolicitud;
    public LocalDate fechaEmision;
    public String emitidoPor;
    
    // Campos de Auditoría (siguiendo el patrón de tu ejemplo)
    public LocalDateTime fechaCreacion;
    public String usuarioCreacion;
    public LocalDateTime fechaModificacion;
    public String usuarioModificacion;

    // Nota: El id_Informe (PK) se omite ya que es autogenerado.
}