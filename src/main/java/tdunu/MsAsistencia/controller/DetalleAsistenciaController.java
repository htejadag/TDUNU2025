package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsAsistencia.model.request.DetalleAsistenciaRequest;
import tdunu.MsAsistencia.model.request.DetalleAsistenciaMasivoRequest;
import tdunu.MsAsistencia.model.response.DetalleAsistenciaResponse;
import tdunu.MsAsistencia.service.DetalleAsistenciaService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.DetalleAsistencia.BASE)
@Tag(name = "Detalle Asistencia", description = "API de Detalle de Asistencia (Registro individual)")
public class DetalleAsistenciaController {

    @Autowired
    private DetalleAsistenciaService detalleAsistenciaService;

    @GetMapping(ApiRoutes.DetalleAsistencia.LISTAR)
    @Operation(summary = "Listar todos los detalles de asistencia")
    public ResponseBase<List<DetalleAsistenciaResponse>> listar() {
        List<DetalleAsistenciaResponse> lista = detalleAsistenciaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.DetalleAsistencia.OBTENER_POR_ID)
    @Operation(summary = "Obtener detalle de asistencia por ID")
    public ResponseBase<DetalleAsistenciaResponse> obtenerPorId(@RequestParam Integer id) {
        DetalleAsistenciaResponse response = detalleAsistenciaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.DetalleAsistencia.GUARDAR)
    @Operation(summary = "Guardar nuevo detalle de asistencia")
    public ResponseBase<DetalleAsistenciaResponse> guardar(@RequestBody DetalleAsistenciaRequest request) {
        DetalleAsistenciaResponse response = detalleAsistenciaService.guardar(request);
        return ResponseBase.ok("Detalle de asistencia registrado", response);
    }

    @PutMapping(ApiRoutes.DetalleAsistencia.ACTUALIZAR)
    @Operation(summary = "Actualizar detalle de asistencia (cambia estado)")
    public ResponseBase<DetalleAsistenciaResponse> actualizar(
            @RequestParam Integer id, 
            @RequestParam Integer usuarioModificacion,
            @RequestBody DetalleAsistenciaRequest request) {
        DetalleAsistenciaResponse response = detalleAsistenciaService.actualizar(id, request, usuarioModificacion);
        return ResponseBase.ok("Detalle de asistencia actualizado", response);
    }

    @DeleteMapping(ApiRoutes.DetalleAsistencia.ELIMINAR)
    @Operation(summary = "Eliminar detalle de asistencia")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        detalleAsistenciaService.eliminar(id);
        return ResponseBase.ok("Detalle de asistencia eliminado", null);
    }

    @GetMapping(ApiRoutes.DetalleAsistencia.BUSCAR_POR_ASISTENCIA)
    @Operation(summary = "Buscar detalles por asistencia (sesión)")
    public ResponseBase<List<DetalleAsistenciaResponse>> buscarPorAsistencia(@RequestParam Integer asistenciaId) {
        List<DetalleAsistenciaResponse> lista = detalleAsistenciaService.buscarPorAsistencia(asistenciaId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.DetalleAsistencia.BUSCAR_POR_ENTIDAD)
    @Operation(summary = "Buscar detalles por entidad (alumno, docente, etc.)")
    public ResponseBase<List<DetalleAsistenciaResponse>> buscarPorEntidad(
            @RequestParam String tipoEntidad, 
            @RequestParam Integer entidadId) {
        List<DetalleAsistenciaResponse> lista = detalleAsistenciaService.buscarPorEntidad(tipoEntidad, entidadId);
        return ResponseBase.ok(lista);
    }

    @PostMapping(ApiRoutes.DetalleAsistencia.REGISTRAR_MASIVO)
    @Operation(summary = "Registrar asistencia masiva (varios alumnos a la vez)")
    public ResponseBase<List<DetalleAsistenciaResponse>> registrarMasivo(@RequestBody DetalleAsistenciaMasivoRequest request) {
        List<DetalleAsistenciaResponse> responses = detalleAsistenciaService.registrarMasivo(request);
        return ResponseBase.ok("Asistencias registradas masivamente", responses);
    }
}
