package msposgrado.Controllers;

import msposgrado.Model.*;
import msposgrado.Service.*;
import msposgrado.Repository.*;
import msposgrado.Constantes.Routes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Routes.TRAMITE)
@Tag(
    name = "Flujo de Trámite",
    description = "Controlador que gestiona el flujo completo de creación de expediente, solicitud y documento"
)
public class FlujoTramiteController {

    private final ExpedienteService expedienteService;
    private final SolicitudService solicitudService;
    private final DocumentoService documentoService;
    private final AsesorService asesorService;
    private final TipoSolicitudRepository tipoSolicitudRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public FlujoTramiteController(
            ExpedienteService expedienteService,
            SolicitudService solicitudService,
            DocumentoService documentoService,
            AsesorService asesorService,
            TipoSolicitudRepository tipoSolicitudRepository,
            EstadoSolicitudRepository estadoSolicitudRepository) {

        this.expedienteService = expedienteService;
        this.solicitudService = solicitudService;
        this.documentoService = documentoService;
        this.asesorService = asesorService;
        this.tipoSolicitudRepository = tipoSolicitudRepository;
        this.estadoSolicitudRepository = estadoSolicitudRepository;
    }

    @PostMapping("/crear-expediente")
    @Operation(
        summary = "Crear expediente completo",
        description = "Crea un expediente, genera automáticamente una solicitud y registra el documento correspondiente dentro del flujo de trámite"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente, solicitud y documento creados correctamente",
            content = @Content(schema = @Schema(implementation = Map.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno durante el flujo de trámite",
            content = @Content
        )
    })
    public Map<String, Object> crearExpedienteCompleto(
            @Parameter(
                description = "Mapa de datos necesarios para crear el expediente. " +
                            "Incluye obligatoriamente: idEstudiante. " +
                            "Opcionalmente puede incluir: idAsesor",
                required = true
            )
            @RequestBody Map<String, Object> request) {

        Map<String, Object> response = new HashMap<>();

        try {

            // ====================================================
            // 1. CREAR EXPEDIENTE
            // ====================================================
            Expediente expediente = new Expediente();
            expediente.setIdEstudiante((Integer) request.get("idEstudiante"));
            expediente.setEstadoExpediente("INICIADO");
            expediente.setFechaApertura(LocalDateTime.now());
            expediente.setObservaciones("Expediente generado automáticamente.");

            expediente = expedienteService.crear(expediente);

            // ====================================================
            // 2. CREAR SOLICITUD
            // ====================================================
            Solicitud solicitud = new Solicitud();
            solicitud.setExpediente(expediente);

            TipoSolicitud tipo = tipoSolicitudRepository.findByNombre("DESIGNACION_ASESOR")
                    .orElseThrow(() -> new RuntimeException(
                            "Error: Tipo Solicitud DESIGNACION_ASESOR no encontrado"));

            solicitud.setTipoSolicitud(tipo);

            EstadoSolicitud estado = estadoSolicitudRepository.findByNombre("PENDIENTE")
                    .orElseThrow(() -> new RuntimeException(
                            "Error: Estado Solicitud PENDIENTE no encontrado"));

            solicitud.setEstadoSolicitud(estado);
            solicitud.setDescripcion(
                    "Solicitud generada automáticamente al registrar expediente.");

            solicitud = solicitudService.crear(solicitud);

            // ====================================================
            // 3. CREAR DOCUMENTO
            // ====================================================
            Documento documento = new Documento();
            documento.setSolicitud(solicitud);
            documento.setTipoDocumento("DESIGNACION_ASESOR");
            documento.setArchivoRuta(
                    "documentos/designacion_asesor_" + expediente.getIdExpediente() + ".pdf");

            documento = documentoService.crear(documento);

            // ====================================================
            // RESPUESTA COMPLETA
            // ====================================================
            response.put("expediente", expediente);
            response.put("solicitud", solicitud);
            response.put("documento", documento);
            response.put("mensaje",
                    "Expediente, solicitud y documento creados correctamente.");

            return response;

        } catch (Exception e) {
            response.put("error", e.getMessage());
            return response;
        }
    }
}