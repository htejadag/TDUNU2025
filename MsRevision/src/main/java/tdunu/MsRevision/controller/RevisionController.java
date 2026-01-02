package tdunu.MsRevision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdunu.MsTitulacion.model.request.RevisionRequest;
import tdunu.MsTitulacion.model.response.RevisionResponse;
import tdunu.MsTitulacion.service.RevisionService;
import tdunu.MsTitulacion.util.ApiRoutes;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    /**
     * Registra una nueva revisión o dictamen.
     * Ruta: /api/titulacion/revision/registrar
     */
    @PostMapping(ApiRoutes.Revision.BASE + ApiRoutes.Revision.REGISTRAR)
    public ResponseEntity<RevisionResponse> registrarRevision(@RequestBody RevisionRequest request) {
        RevisionResponse response = revisionService.registrarRevision(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtiene todo el historial de revisiones de un proyecto.
     * Ruta: /api/titulacion/revision/proyecto/{idProyecto}
     */
    @GetMapping(ApiRoutes.Revision.BASE + ApiRoutes.Revision.POR_PROYECTO)
    public ResponseEntity<List<RevisionResponse>> listarPorProyecto(@PathVariable Integer idProyecto) {
        List<RevisionResponse> response = revisionService.listarHistorialPorProyecto(idProyecto);
        return ResponseEntity.ok(response);
    }

    /**
     * (Opcional) Obtiene las revisiones hechas por un revisor específico.
     * Ruta: /api/titulacion/revision/revisor/{idRevisor}
     */
    @GetMapping(ApiRoutes.Revision.BASE + ApiRoutes.Revision.POR_REVISOR)
    public ResponseEntity<List<RevisionResponse>> listarPorRevisor(@PathVariable Integer idRevisor) {
        List<RevisionResponse> response = revisionService.listarRevisionesPorRevisor(idRevisor);
        return ResponseEntity.ok(response);
    }
}