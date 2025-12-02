package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdunu.MsAsistencia.model.request.AsistenciaRequest;
import tdunu.MsAsistencia.model.response.AsistenciaResponse;
import tdunu.MsAsistencia.service.AsistenciaService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Asistencia.BASE)
@Tag(name = "Asistencia", description = "API de Asistencia Generica y Reutilizable")
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
    @Operation(summary = "Guardar una nueva asistencia")
    public ResponseBase<AsistenciaResponse> guardar(@RequestBody AsistenciaRequest request) {
        AsistenciaResponse response = asistenciaService.guardar(request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(ApiRoutes.Asistencia.ELIMINAR)
    @Operation(summary = "Eliminar una asistencia")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        asistenciaService.eliminar(id);
        return ResponseBase.ok(null);
    }

    @GetMapping(ApiRoutes.Asistencia.BUSCAR_POR_ENTIDAD)
    @Operation(summary = "Buscar asistencias por tipo de entidad y ID")
    public ResponseBase<List<AsistenciaResponse>> buscarPorEntidad(
            @RequestParam String tipoEntidad,
            @RequestParam Integer entidadId) {
        List<AsistenciaResponse> lista = asistenciaService.buscarPorEntidad(tipoEntidad, entidadId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Asistencia.BUSCAR_POR_EVENTO)
    @Operation(summary = "Buscar asistencias por tipo de evento y ID")
    public ResponseBase<List<AsistenciaResponse>> buscarPorEvento(
            @RequestParam String tipoEvento,
            @RequestParam Integer eventoId) {
        List<AsistenciaResponse> lista = asistenciaService.buscarPorEvento(tipoEvento, eventoId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Asistencia.BUSCAR_POR_FECHA)
    @Operation(summary = "Buscar asistencias por rango de fechas")
    public ResponseBase<List<AsistenciaResponse>> buscarPorFecha(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {
        List<AsistenciaResponse> lista = asistenciaService.buscarPorFecha(inicio, fin);
        return ResponseBase.ok(lista);
    }
}
