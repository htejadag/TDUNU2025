package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsAsistencia.model.response.HistorialAsistenciaResponse;
import tdunu.MsAsistencia.service.HistorialAsistenciaService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.HistorialAsistencia.BASE)
@Tag(name = "Historial Asistencia", description = "API de Historial de Cambios de Asistencia")
public class HistorialAsistenciaController {

    @Autowired
    private HistorialAsistenciaService historialAsistenciaService;

    @GetMapping(ApiRoutes.HistorialAsistencia.LISTAR)
    @Operation(summary = "Listar todo el historial de asistencias")
    public ResponseBase<List<HistorialAsistenciaResponse>> listar() {
        List<HistorialAsistenciaResponse> lista = historialAsistenciaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.HistorialAsistencia.OBTENER_POR_ID)
    @Operation(summary = "Obtener historial por ID")
    public ResponseBase<HistorialAsistenciaResponse> obtenerPorId(@RequestParam Integer id) {
        HistorialAsistenciaResponse response = historialAsistenciaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.HistorialAsistencia.BUSCAR_POR_DETALLE)
    @Operation(summary = "Buscar historial por detalle de asistencia")
    public ResponseBase<List<HistorialAsistenciaResponse>> buscarPorDetalle(@RequestParam Integer detalleAsistenciaId) {
        List<HistorialAsistenciaResponse> lista = historialAsistenciaService.buscarPorDetalle(detalleAsistenciaId);
        return ResponseBase.ok(lista);
    }
}
