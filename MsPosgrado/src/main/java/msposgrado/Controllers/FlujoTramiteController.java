package msposgrado.Controllers;

import msposgrado.Model.*;
import msposgrado.Service.*;
import msposgrado.Repository.*;
import msposgrado.Constantes.Routes;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Routes.TRAMITE)
public class FlujoTramiteController {

    private final ExpedienteService expedienteService;
    private final SolicitudService solicitudService;
    private final DocumentoService documentoService;
    private final AsesorService asesorService;
    private final TipoSolicitudRepository tipoSolicitudRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public FlujoTramiteController(ExpedienteService expedienteService,
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
    public Map<String, Object> crearExpedienteCompleto(@RequestBody Map<String, Object> request) {

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

            // Asignar asesor si viene en el JSON
            /*
             * if (request.containsKey("idAsesor")) {
             * Integer idAsesor = (Integer) request.get("idAsesor");
             * Asesor asesor = asesorService.obtenerPorId(idAsesor);
             * expediente.setAsesor(asesor);
             * }
             */

            expediente = expedienteService.crear(expediente);

            // ====================================================
            // 2. CREAR SOLICITUD
            // ====================================================
            Solicitud solicitud = new Solicitud();
            solicitud.setExpediente(expediente);
            // Buscar Tipo
            TipoSolicitud tipo = tipoSolicitudRepository.findByNombre("DESIGNACION_ASESOR")
                    .orElseThrow(() -> new RuntimeException("Error: Tipo Solicitud DESIGNACION_ASESOR no encontrado"));
            solicitud.setTipoSolicitud(tipo);

            // Buscar Estado
            EstadoSolicitud estado = estadoSolicitudRepository.findByNombre("PENDIENTE")
                    .orElseThrow(() -> new RuntimeException("Error: Estado Solicitud PENDIENTE no encontrado"));
            solicitud.setEstadoSolicitud(estado);
            solicitud.setDescripcion("Solicitud generada automáticamente al registrar expediente.");

            solicitud = solicitudService.crear(solicitud);

            // ====================================================
            // 3. CREAR DOCUMENTO
            // ====================================================
            Documento documento = new Documento();
            documento.setSolicitud(solicitud);
            documento.setTipoDocumento("DESIGNACION_ASESOR");
            documento.setArchivoRuta("documentos/designacion_asesor_" + expediente.getIdExpediente() + ".pdf");
            // documento.setFechaDocumento(LocalDateTime.now());

            documento = documentoService.crear(documento);

            // ====================================================
            // RESPUESTA COMPLETA
            // ====================================================
            response.put("expediente", expediente);
            response.put("solicitud", solicitud);
            response.put("documento", documento);
            response.put("mensaje", "Expediente, solicitud y documento creados correctamente.");

            return response;

        } catch (Exception e) {
            response.put("error", e.getMessage());
            return response;
        }
    }
}
