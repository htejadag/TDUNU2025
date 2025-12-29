package com.unu.ms.MsConsejo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.model.response.SesionReportePeriodoResponse;
import com.unu.ms.MsConsejo.service.SesionConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.SesionConsejo.BASE)
@Tag(name = "SesionConsejo Controller")
@RequiredArgsConstructor
public class SesionConsejoController {

    private final SesionConsejoService sesionConsejoService;

    @GetMapping(value = ApiRoutes.SesionConsejo.LISTAR)
    public ResponseBase<List<SesionConsejoResponse>> listar() {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.SesionConsejo.GUARDAR)
    public ResponseBase<SesionConsejoResponse> guardar(@RequestBody SesionConsejoRequest request) {
        SesionConsejoResponse response = sesionConsejoService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.SesionConsejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {
        sesionConsejoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.SesionConsejo.ACTUALIZAR)
    public ResponseBase<SesionConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SesionConsejoRequest request) {
        SesionConsejoResponse response = sesionConsejoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.OBTENER_POR_ID)
    public ResponseBase<SesionConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        SesionConsejoResponse response = sesionConsejoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorConsejo(idConsejo);
        return ResponseBase.ok(listaResponse);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_NUMERO)
    public ResponseBase<SesionConsejoResponse> buscarPorNumeroSesion(@RequestParam String numeroSesion) {
        SesionConsejoResponse response = sesionConsejoService.buscarPorNumeroSesion(numeroSesion);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_FECHA)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSesion) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorFecha(fechaSesion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_TIPO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorTipoSesion(@RequestParam Integer idTipoSesion) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorTipoSesion(idTipoSesion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    // ==================== NUEVOS ENDPOINTS ====================

    /**
     * Obtiene todas las sesiones de un Consejo filtradas por id_tipo_sesion
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.POR_CONSEJO_Y_TIPO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorConsejoYTipo(
            @PathVariable("id") Integer idConsejo,
            @PathVariable("tipoId") Integer tipoId) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorConsejoYTipo(idConsejo, tipoId);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Lista las sesiones pendientes (fecha_sesion en el futuro y estado 'Programada')
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.PENDIENTES_POR_CONSEJO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPendientesPorConsejo(
            @PathVariable("id") Integer idConsejo) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPendientesPorConsejo(idConsejo);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Cambia el id_estado de la sesión a 'Finalizada'
     */
    @PostMapping(value = ApiRoutes.SesionConsejo.FINALIZAR)
    public ResponseBase<SesionConsejoResponse> finalizarSesion(
            @PathVariable("id") Integer idSesion) {
        SesionConsejoResponse response = sesionConsejoService.finalizarSesion(idSesion);
        return ResponseBase.ok("Sesión finalizada correctamente", response);
    }

    /**
     * Obtiene un resumen de sesiones realizadas en un rango de fechas
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.REPORTE_PERIODO)
    public ResponseBase<SesionReportePeriodoResponse> obtenerReportePeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        SesionReportePeriodoResponse response = sesionConsejoService.obtenerReportePeriodo(fechaInicio, fechaFin);
        return ResponseBase.ok(Mensajes.LISTAR_OK, response);
    }

}
