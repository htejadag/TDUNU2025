package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tdunu.MsAsistencia.model.request.AsistenciaRequest;
import tdunu.MsAsistencia.model.response.AsistenciaResponse;
import tdunu.MsAsistencia.service.AsistenciaService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Asistencia.BASE)
@Tag(name = "Asistencia", description = "API de Asistencia (Sesiones/Fechas)")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping(ApiRoutes.Asistencia.LISTAR)
    @Operation(summary = "Listar todas las asistencias")
    public ResponseBase<List<AsistenciaResponse>> listar() {
        List<AsistenciaResponse> lista = asistenciaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Asistencia.OBTENER_POR_ID)
    @Operation(summary = "Obtener asistencia por ID")
    public ResponseBase<AsistenciaResponse> obtenerPorId(@RequestParam Integer id) {
        AsistenciaResponse response = asistenciaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.Asistencia.GUARDAR)
    @Operation(summary = "Guardar nueva asistencia (sesión)")
    public ResponseBase<AsistenciaResponse> guardar(@RequestBody AsistenciaRequest request) {
        AsistenciaResponse response = asistenciaService.guardar(request);
        return ResponseBase.ok("Asistencia registrada correctamente", response);
    }

    @PutMapping(ApiRoutes.Asistencia.ACTUALIZAR)
    @Operation(summary = "Actualizar asistencia existente")
    public ResponseBase<AsistenciaResponse> actualizar(@RequestParam Integer id, @RequestBody AsistenciaRequest request) {
        AsistenciaResponse response = asistenciaService.actualizar(id, request);
        return ResponseBase.ok("Asistencia actualizada correctamente", response);
    }

    @DeleteMapping(ApiRoutes.Asistencia.ELIMINAR)
    @Operation(summary = "Eliminar asistencia")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        asistenciaService.eliminar(id);
        return ResponseBase.ok("Asistencia eliminada correctamente", null);
    }

    @GetMapping(ApiRoutes.Asistencia.BUSCAR_POR_PROGRAMACION)
    @Operation(summary = "Buscar asistencias por programación")
    public ResponseBase<List<AsistenciaResponse>> buscarPorProgramacion(@RequestParam Integer programacionId) {
        List<AsistenciaResponse> lista = asistenciaService.buscarPorProgramacion(programacionId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Asistencia.BUSCAR_POR_FECHA)
    @Operation(summary = "Buscar asistencias por fecha")
    public ResponseBase<List<AsistenciaResponse>> buscarPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<AsistenciaResponse> lista = asistenciaService.buscarPorFecha(fecha);
        return ResponseBase.ok(lista);
    }
}
