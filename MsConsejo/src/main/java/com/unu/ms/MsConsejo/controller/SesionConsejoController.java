package com.unu.ms.MsConsejo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para la gestión de sesiones del consejo.
 * 
 * Proporciona endpoints para realizar operaciones CRUD sobre las sesiones
 * de los consejos. Las sesiones son reuniones periódicas donde se registra
 * la asistencia de los miembros del consejo.
 * 
 * Endpoints principales:
 * - GET /sesion-consejo - Listar todas las sesiones
 * - GET /sesion-consejo?id={id} - Obtener sesión por ID
 * - GET /sesion-consejo/numero?numeroSesion={num} - Buscar por número de sesión
 * - GET /sesion-consejo/consejo?idConsejo={id} - Buscar sesiones por consejo
 * - GET /sesion-consejo/fecha?fechaSesion={fecha} - Buscar sesiones por fecha
 * - POST /sesion-consejo - Crear nueva sesión
 * - PUT /sesion-consejo?id={id} - Actualizar sesión
 * - DELETE /sesion-consejo?id={id} - Eliminar sesión
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.SesionConsejo.BASE)
@Tag(name = "SesionConsejo Controller")
public class SesionConsejoController {

    /** Servicio para la gestión de operaciones de sesiones de consejo */
    private SesionConsejoService sesionConsejoService;

    /**
     * Lista todas las sesiones de consejo disponibles en el sistema.
     * 
     * @return ResponseBase que contiene una lista de todas las sesiones
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.LISTAR)
    public ResponseBase<List<SesionConsejoResponse>> listar() {

        log.info("Inicio request: listar sesiones de consejo");

        List<SesionConsejoResponse> listaResponse = sesionConsejoService.listar();

        log.info(
                "Fin request: listar sesiones de consejo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Crea una nueva sesión de consejo en el sistema.
     * Registra la acción en auditoría y envía evento a Kafka.
     * 
     * @param request datos de la sesión a crear (número, nombre, fecha, tipo, etc.)
     * @return ResponseBase con la sesión creada incluyendo su ID generado
     */
    @PostMapping(value = ApiRoutes.SesionConsejo.GUARDAR)
    public ResponseBase<SesionConsejoResponse> guardar(@RequestBody SesionConsejoRequest request) {

        log.info("Inicio request: guardar sesión de consejo");
        log.debug("Request guardar sesión consejo: {}", request);

        SesionConsejoResponse response = sesionConsejoService.guardar(request);
       
        log.info(
                "Fin request: guardar sesión de consejo. Id generado: {}",
                response.getIdSesion()
        );

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina una sesión de consejo del sistema.
     * Registra la acción en auditoría.
     * 
     * @param id identificador de la sesión a eliminar
     * @return ResponseBase indicando éxito de la eliminación
     */
    @DeleteMapping(value = ApiRoutes.SesionConsejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar sesión de consejo");
        log.debug("Id sesión consejo a eliminar: {}", id);

        sesionConsejoService.eliminar(id);

        log.info(
                "Fin request: eliminar sesión de consejo. Id eliminado: {}",
                id
        );

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza los datos de una sesión de consejo existente.
     * Registra la acción en auditoría con los datos antes y después.
     * 
     * @param id identificador de la sesión a actualizar
     * @param request nuevos datos de la sesión
     * @return ResponseBase con la sesión actualizada
     */
    @PutMapping(value = ApiRoutes.SesionConsejo.ACTUALIZAR)
    public ResponseBase<SesionConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SesionConsejoRequest request) {

        log.info("Inicio request: actualizar sesión de consejo");
        log.debug("Id sesión consejo a actualizar: {}", id);
        log.debug("Request actualizar sesión consejo: {}", request);

        SesionConsejoResponse response = sesionConsejoService.actualizar(id, request);

        log.info(
                "Fin request: actualizar sesión de consejo. Id actualizado: {}",
                response.getIdSesion()
        );

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene una sesión de consejo específica por su identificador.
     * 
     * @param id identificador de la sesión a obtener
     * @return ResponseBase con la sesión encontrada
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.OBTENER_POR_ID)
    public ResponseBase<SesionConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener sesión de consejo por id");
        log.debug("Id sesión consejo: {}", id);

        SesionConsejoResponse response = sesionConsejoService.obtenerPorId(id);

        log.info("Fin request: obtener sesión de consejo por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca todas las sesiones que pertenecen a un consejo específico.
     * 
     * @param idConsejo identificador del consejo
     * @return ResponseBase con lista de sesiones del consejo especificado
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {

        log.info("Inicio request: buscar sesiones por consejo");
        log.debug("Id consejo: {}", idConsejo);

        List<SesionConsejoResponse> listaResponse =
                sesionConsejoService.buscarPorConsejo(idConsejo);

        log.info(
                "Fin request: buscar sesiones por consejo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca una sesión de consejo específica por su número de sesión único.
     * 
     * @param numeroSesion número identificador único de la sesión
     * @return ResponseBase con la sesión encontrada
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_NUMERO)
    public ResponseBase<SesionConsejoResponse> buscarPorNumeroSesion(@RequestParam String numeroSesion) {

        log.info("Inicio request: buscar sesión por número");
        log.debug("Número sesión: {}", numeroSesion);

        SesionConsejoResponse response =
                sesionConsejoService.buscarPorNumeroSesion(numeroSesion);

        log.info("Fin request: buscar sesión por número");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca todas las sesiones que se realizaron en una fecha específica.
     * 
     * @param fechaSesion fecha de las sesiones a buscar
     * @return ResponseBase que contiene una lista de sesiones de la fecha especificada
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_FECHA)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSesion) {

        log.info("Inicio request: buscar sesiones por fecha");
        log.debug("Fecha sesión: {}", fechaSesion);

        List<SesionConsejoResponse> listaResponse =
                sesionConsejoService.buscarPorFecha(fechaSesion);

        log.info(
                "Fin request: buscar sesiones por fecha. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca todas las sesiones de un tipo específico.
     * 
     * @param idTipoSesion identificador del tipo de sesión
     * @return ResponseBase que contiene una lista de sesiones del tipo especificado
     */
    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_TIPO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorTipoSesion(@RequestParam Integer idTipoSesion) {

        log.info("Inicio request: buscar sesiones por tipo");
        log.debug("Id tipo sesión: {}", idTipoSesion);

        List<SesionConsejoResponse> listaResponse =
                sesionConsejoService.buscarPorTipoSesion(idTipoSesion);

        log.info(
                "Fin request: buscar sesiones por tipo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}