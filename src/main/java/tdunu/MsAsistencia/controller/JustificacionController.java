package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsAsistencia.model.request.JustificacionRequest;
import tdunu.MsAsistencia.model.response.JustificacionResponse;
import tdunu.MsAsistencia.service.JustificacionService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Justificacion.BASE)
@Tag(name = "Justificación", description = "API de Justificaciones de Faltas")
public class JustificacionController {

    @Autowired
    private JustificacionService justificacionService;

    @GetMapping(ApiRoutes.Justificacion.LISTAR)
    @Operation(summary = "Listar todas las justificaciones")
    public ResponseBase<List<JustificacionResponse>> listar() {
        List<JustificacionResponse> lista = justificacionService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Justificacion.OBTENER_POR_ID)
    @Operation(summary = "Obtener justificación por ID")
    public ResponseBase<JustificacionResponse> obtenerPorId(@RequestParam Integer id) {
        JustificacionResponse response = justificacionService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.Justificacion.GUARDAR)
    @Operation(summary = "Guardar nueva justificación")
    public ResponseBase<JustificacionResponse> guardar(@RequestBody JustificacionRequest request) {
        JustificacionResponse response = justificacionService.guardar(request);
        return ResponseBase.ok("Justificación registrada correctamente", response);
    }

    @PutMapping(ApiRoutes.Justificacion.ACTUALIZAR)
    @Operation(summary = "Actualizar justificación existente")
    public ResponseBase<JustificacionResponse> actualizar(@RequestParam Integer id, @RequestBody JustificacionRequest request) {
        JustificacionResponse response = justificacionService.actualizar(id, request);
        return ResponseBase.ok("Justificación actualizada correctamente", response);
    }

    @DeleteMapping(ApiRoutes.Justificacion.ELIMINAR)
    @Operation(summary = "Eliminar justificación")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        justificacionService.eliminar(id);
        return ResponseBase.ok("Justificación eliminada correctamente", null);
    }

    @PutMapping(ApiRoutes.Justificacion.APROBAR)
    @Operation(summary = "Aprobar justificación (cambia estado a JUSTIFICADO)")
    public ResponseBase<JustificacionResponse> aprobar(@RequestParam Integer id, @RequestParam Integer usuarioModificacion) {
        JustificacionResponse response = justificacionService.aprobar(id, usuarioModificacion);
        return ResponseBase.ok("Justificación aprobada", response);
    }

    @PutMapping(ApiRoutes.Justificacion.RECHAZAR)
    @Operation(summary = "Rechazar justificación")
    public ResponseBase<JustificacionResponse> rechazar(@RequestParam Integer id, @RequestParam Integer usuarioModificacion) {
        JustificacionResponse response = justificacionService.rechazar(id, usuarioModificacion);
        return ResponseBase.ok("Justificación rechazada", response);
    }

    @GetMapping(ApiRoutes.Justificacion.BUSCAR_PENDIENTES)
    @Operation(summary = "Buscar justificaciones pendientes de aprobación")
    public ResponseBase<List<JustificacionResponse>> buscarPendientes() {
        List<JustificacionResponse> lista = justificacionService.buscarPendientes();
        return ResponseBase.ok(lista);
    }
}
