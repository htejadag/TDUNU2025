package tdunu.MsSolicitudes.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tdunu.MsSolicitudes.model.entity.SolicitudesModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoResponse {
    private Integer idProceso;
    private Integer idEstudiante;
    private String procCodigo;
    private LocalDate procFechaInicio;
    private LocalDate procFechaFin;
    private String procEstado;
    private String procFaseActual;
    private String procObservaciones;
    
    // Fechas del flujo
    private LocalDate procFechaPagoFicha;
    private LocalDate procFechaEmisionFicha;
    private LocalDate procFechaPagoInforme;
    private LocalDate procFechaSolicitudInforme;
    private LocalDate procFechaEmisionInforme;
    private LocalDate procFechaSolicitudReingreso;
    private LocalDate procFechaResolucion;
    private LocalDate procFechaMatricula;
    
    // Auditoría
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
    
    // Constructor desde Entity
    public static DemoResponse fromEntity(SolicitudesModel entity) {
        return DemoResponse.builder()
                .idProceso(entity.getIdProceso())
                .idEstudiante(entity.getIdEstudiante())
                .procCodigo(entity.getProcCodigo())
                .procFechaInicio(entity.getProcFechaInicio())
                .procFechaFin(entity.getProcFechaFin())
                .procEstado(entity.getProcEstado())
                .procFaseActual(entity.getProcFaseActual())
                .procObservaciones(entity.getProcObservaciones())
                .procFechaPagoFicha(entity.getProcFechaPagoFicha())
                .procFechaEmisionFicha(entity.getProcFechaEmisionFicha())
                .procFechaPagoInforme(entity.getProcFechaPagoInforme())
                .procFechaSolicitudInforme(entity.getProcFechaSolicitudInforme())
                .procFechaEmisionInforme(entity.getProcFechaEmisionInforme())
                .procFechaSolicitudReingreso(entity.getProcFechaSolicitudReingreso())
                .procFechaResolucion(entity.getProcFechaResolucion())
                .procFechaMatricula(entity.getProcFechaMatricula())
                .fechaCreacion(entity.getFechaCreacion())
                .usuarioCreacion(entity.getUsuarioCreacion())
                .fechaModificacion(entity.getFechaModificacion())
                .usuarioModificacion(entity.getUsuarioModificacion())
                .build();
    }
}