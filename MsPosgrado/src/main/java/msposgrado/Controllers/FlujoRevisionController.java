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
@RequestMapping(Routes.REVISION)
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
    public Map<String, Object> registrarRevisionJurado(@RequestBody Map<String, Object> req) {

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
            // doc.setFechaDocumento(LocalDateTime.now());

            // Creamos solicitud automática
            Solicitud solicitud = new Solicitud();
            solicitud.setExpediente(tesis.getExpediente());
            // Fecha se asigna automáticamente en auditoría
            // Buscar Estado FINALIZADO
            EstadoSolicitud estado = estadoSolicitudRepository.findByNombre("FINALIZADO")
                    .orElseGet(() -> {
                        // Fallback si no existe en data inicial
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
