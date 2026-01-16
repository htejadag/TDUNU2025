package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;
import com.unu.ms.MsConsejo.service.AsistenciaSesionService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para la gestión de asistencia en sesiones de consejo.
 * 
 * Proporciona endpoints para registrar y consultar la asistencia de los
 * miembros del consejo en las sesiones. Permite marcar presencia, ausencia
 * y otras variantes de asistencia durante las reuniones.
 * 
 * Endpoints disponibles:
 * - GET /asistencia-sesion - Listar todos los registros de asistencia
 * - GET /asistencia-sesion?id={id} - Obtener registro de asistencia por ID
 * - GET /asistencia-sesion/sesion?idSesion={id} - Buscar asistencias de una sesión
 * - GET /asistencia-sesion/miembro?idMiembro={id} - Buscar asistencias de un miembro
 * - POST /asistencia-sesion - Registrar nueva asistencia
 * - PUT /asistencia-sesion?id={id} - Actualizar registro de asistencia
 * - DELETE /asistencia-sesion?id={id} - Eliminar registro de asistencia
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.AsistenciaSesion.BASE)
@Tag(name = "AsistenciaSesion Controller")
public class AsistenciaSesionController {

    /** Servicio para la gestión de operaciones de asistencia en sesiones */
    private AsistenciaSesionService asistenciaSesionService;

    /**
     * Lista todos los registros de asistencia en sesiones disponibles en el sistema.
     * 
     * @return respuesta base con lista completa de registros de asistencia
     */
    @GetMapping(value = ApiRoutes.AsistenciaSesion.LISTAR)
    public ResponseBase<List<AsistenciaSesionResponse>> listar() {

        log.info("Inicio request: listar asistencias de sesion");

        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.listar();

        log.info("Fin request: listar asistencias de sesion. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Registra una nueva asistencia en el sistema para una sesión.
     * 
     * @param request datos del registro de asistencia (miembro, sesión, estado, etc.)
     * @return respuesta base con el registro de asistencia creado incluyendo su ID generado
     */
    @PostMapping(value = ApiRoutes.AsistenciaSesion.GUARDAR)
    public ResponseBase<AsistenciaSesionResponse> guardar(@RequestBody AsistenciaSesionRequest request) {

        log.info("Inicio request: guardar asistencia de sesion");
        log.debug("Request guardar asistencia de sesion: {}", request);

        AsistenciaSesionResponse response = asistenciaSesionService.guardar(request);

        log.info("Fin request: guardar asistencia de sesion. Id generado: {}", response.getIdAsistencia());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina un registro de asistencia del sistema.
     * 
     * @param id identificador del registro de asistencia a eliminar
     * @return respuesta base indicando éxito de la eliminación
     */
    @DeleteMapping(value = ApiRoutes.AsistenciaSesion.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar asistencia de sesion");
        log.debug("Id asistencia sesion a eliminar: {}", id);

        asistenciaSesionService.eliminar(id);

        log.info("Fin request: eliminar asistencia de sesion. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza los datos de un registro de asistencia existente.
     * 
     * @param id identificador del registro a actualizar
     * @param request nuevos datos del registro de asistencia
     * @return respuesta base con el registro de asistencia actualizado
     */
    @PutMapping(value = ApiRoutes.AsistenciaSesion.ACTUALIZAR)
    public ResponseBase<AsistenciaSesionResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody AsistenciaSesionRequest request) {

        log.info("Inicio request: actualizar asistencia de sesion");
        log.debug("Id asistencia sesion a actualizar: {}", id);
        log.debug("Request actualizar asistencia de sesion: {}", request);

        AsistenciaSesionResponse response = asistenciaSesionService.actualizar(id, request);

        log.info("Fin request: actualizar asistencia de sesion. Id actualizado: {}", response.getIdAsistencia());

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene un registro de asistencia específico por su identificador.
     * 
     * @param id identificador del registro de asistencia a obtener
     * @return respuesta base con el registro de asistencia encontrado
     */
    @GetMapping(value = ApiRoutes.AsistenciaSesion.OBTENER_POR_ID)
    public ResponseBase<AsistenciaSesionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener asistencia de sesion por id");
        log.debug("Id asistencia sesion: {}", id);

        AsistenciaSesionResponse response = asistenciaSesionService.obtenerPorId(id);

        log.info("Fin request: obtener asistencia de sesion por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca todos los registros de asistencia de una sesión específica.
     * 
     * @param idSesion identificador de la sesión
     * @return respuesta base con lista de asistencias de esa sesión
     */
    @GetMapping(value = ApiRoutes.AsistenciaSesion.BUSCAR_POR_SESION)
    public ResponseBase<List<AsistenciaSesionResponse>> buscarPorSesion(@RequestParam Integer idSesion) {

        log.info("Inicio request: buscar asistencias por sesion");
        log.debug("Id sesion: {}", idSesion);

        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.buscarPorSesion(idSesion);

        log.info(
                "Fin request: buscar asistencias por sesion. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca todos los registros de asistencia de un miembro específico.
     * 
     * @param idMiembro identificador del miembro del consejo
     * @return respuesta base con lista de asistencias de ese miembro
     */
    @GetMapping(value = ApiRoutes.AsistenciaSesion.BUSCAR_POR_MIEMBRO)
    public ResponseBase<List<AsistenciaSesionResponse>> buscarPorMiembro(@RequestParam Integer idMiembro) {

        log.info("Inicio request: buscar asistencias por miembro");
        log.debug("Id miembro: {}", idMiembro);

        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.buscarPorMiembro(idMiembro);

        log.info(
                "Fin request: buscar asistencias por miembro. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca todos los registros de asistencia con un estado específico.
     * 
     * @param idEstadoAsistencia identificador del estado de asistencia (presente, ausente, etc.)
     * @return respuesta base con lista de asistencias con ese estado
     */
    @GetMapping(value = ApiRoutes.AsistenciaSesion.BUSCAR_POR_ESTADO)
    public ResponseBase<List<AsistenciaSesionResponse>> buscarPorEstadoAsistencia(@RequestParam Integer idEstadoAsistencia) {

        log.info("Inicio request: buscar asistencias por estado");
        log.debug("Id estado asistencia: {}", idEstadoAsistencia);

        List<AsistenciaSesionResponse> listaResponse =
                asistenciaSesionService.buscarPorEstadoAsistencia(idEstadoAsistencia);

        log.info(
                "Fin request: buscar asistencias por estado. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
