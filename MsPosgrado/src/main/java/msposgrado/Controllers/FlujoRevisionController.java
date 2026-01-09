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
@RequestMapping(Routes.REVISION)
@Tag(
    name = "Flujo de Revisión",
    description = "Controlador que gestiona el flujo completo de revisión de tesis por jurado"
)
public class FlujoRevisionController {

    private final TesisService tesisService;
    private final JuradoService juradoService;
    private final RevisionService revisionService;
    private final DocumentoService documentoService;
    private final SolicitudService solicitudService;

    private final TipoSolicitudRepository tipoSolicitudRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    public FlujoRevisionController(
            TesisService tesisService,
            JuradoService juradoService,
            RevisionService revisionService,
            DocumentoService documentoService,
            SolicitudService solicitudService,
            TipoSolicitudRepository tipoSolicitudRepository,
            EstadoSolicitudRepository estadoSolicitudRepository) {

        this.tesisService = tesisService;
        this.juradoService = juradoService;
        this.revisionService = revisionService;
        this.documentoService = documentoService;
        this.solicitudService = solicitudService;
        this.tipoSolicitudRepository = tipoSolicitudRepository;
        this.estadoSolicitudRepository = estadoSolicitudRepository;
    }

    @PostMapping("/realizar")
    @Operation(
        summary = "Registrar revisión de jurado",
        description = "Registra una revisión realizada por un jurado, actualiza el estado de la tesis y genera automáticamente el dictamen y la solicitud asociada"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Revisión registrada correctamente",
            content = @Content(schema = @Schema(implementation = Map.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno durante el proceso de revisión",
            content = @Content
        )
    })
    public Map<String, Object> registrarRevisionJurado(
            @Parameter(
                description = "Mapa de datos necesarios para registrar la revisión. " + 
                "Incluye: idTesis, idJurado, tipoRevision, dictamen y comentario",
                required = true
            )
            @RequestBody Map<String, Object> req) {

        Map<String, Object> res = new HashMap<>();

        try {

            // ===========================
            // 1. Validar TESIS
            // ===========================
            Integer idTesis = (Integer) req.get("idTesis");
            Tesis tesis = tesisService.obtenerPorId(idTesis);

            if (tesis == null) {
                res.put("error", "La tesis con ID " + idTesis + " no existe.");
                return res;
            }

            // ===========================
            // 2. Validar JURADO
            // ===========================
            Integer idJurado = (Integer) req.get("idJurado");
            Jurado jurado = juradoService.obtenerPorId(idJurado);

            if (jurado == null) {
                res.put("error", "El jurado con ID " + idJurado + " no existe.");
                return res;
            }

            // ===========================
            // 3. Crear la REVISIÓN
            // ===========================
            Revision revision = new Revision();
            revision.setTesis(tesis);
            revision.setJurado(jurado);

            revision.setTipoRevision((String) req.get("tipoRevision")); // REVISION_PT o REVISION_IF
            revision.setDictamen((String) req.get("dictamen")); // OBSERVADO o APROBADO
            revision.setComentario((String) req.get("comentario"));
            revision.setFechaRevision(LocalDateTime.now());

            revision = revisionService.crear(revision);

            // ===========================
            // 4. Actualizar ESTADO de la TESIS
            // ===========================
            if (revision.getTipoRevision().equals("REVISION_PT")) {

                if (revision.getDictamen().equals("APROBADO")) {
                    tesis.setEstadoProyecto("APROBADO");
                } else {
                    tesis.setEstadoProyecto("EN_REVISION");
                }

            } else if (revision.getTipoRevision().equals("REVISION_IF")) {

                if (revision.getDictamen().equals("APROBADO")) {
                    tesis.setEstadoInformeFinal("APROBADO");
                } else {
                    tesis.setEstadoInformeFinal("EN_REVISION");
                }
            }

            tesis = tesisService.crear(tesis);

            // ===========================
            // 5. Crear DOCUMENTO DE DICTAMEN
            // ===========================
            Documento doc = new Documento();
            doc.setArchivoRuta("dictamen/dictamen_" + revision.getIdRevision() + ".pdf");
            doc.setTipoDocumento("DICTAMEN_JURADO");

            // Crear solicitud automática
            Solicitud solicitud = new Solicitud();
            solicitud.setExpediente(tesis.getExpediente());

            EstadoSolicitud estado = estadoSolicitudRepository.findByNombre("FINALIZADO")
                    .orElseGet(() -> {
                        EstadoSolicitud nuevo = new EstadoSolicitud();
                        nuevo.setNombre("FINALIZADO");
                        return estadoSolicitudRepository.save(nuevo);
                    });
            solicitud.setEstadoSolicitud(estado);

            TipoSolicitud tipo;
            if (revision.getTipoRevision().equals("REVISION_PT")) {
                tipo = tipoSolicitudRepository.findByNombre("APROBACION_PT")
                        .orElseGet(() -> {
                            TipoSolicitud nuevo = new TipoSolicitud();
                            nuevo.setNombre("APROBACION_PT");
                            return tipoSolicitudRepository.save(nuevo);
                        });
                solicitud.setDescripcion("Dictamen sobre Proyecto de Tesis");
            } else {
                tipo = tipoSolicitudRepository.findByNombre("REVISION_IF")
                        .orElseGet(() -> {
                            TipoSolicitud nuevo = new TipoSolicitud();
                            nuevo.setNombre("REVISION_IF");
                            return tipoSolicitudRepository.save(nuevo);
                        });
                solicitud.setDescripcion("Dictamen del Informe Final");
            }
            solicitud.setTipoSolicitud(tipo);

            solicitud = solicitudService.crear(solicitud);

            doc.setSolicitud(solicitud);
            doc = documentoService.crear(doc);

            // ===========================
            // 6. RESPUESTA COMPLETA
            // ===========================
            res.put("mensaje", "Revisión registrada correctamente");
            res.put("revision", revision);
            res.put("estado_actualizado_tesis", tesis);
            res.put("documento_dictamen", doc);
            res.put("solicitud_generada", solicitud);

            return res;

        } catch (Exception e) {
            res.put("error", e.getMessage());
            return res;
        }
    }
}