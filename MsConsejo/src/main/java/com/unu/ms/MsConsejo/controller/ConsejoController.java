package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.service.ConsejoService;
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
 * Controlador REST para la gestión de consejos.
 * 
 * Proporciona endpoints para realizar operaciones CRUD sobre los consejos del sistema.
 * Un consejo es una estructura organizacional compuesta por miembros que participan
 * en sesiones periódicas.
 * 
 * Endpoints disponibles:
 * - GET /consejo - Listar todos los consejos
 * - GET /consejo?id={id} - Obtener consejo por ID
 * - GET /consejo/nombre?nombre={nombre} - Buscar consejo por nombre
 * - GET /consejo/estado?idEstado={idEstado} - Buscar consejos por estado
 * - POST /consejo - Crear nuevo consejo
 * - PUT /consejo?id={id} - Actualizar consejo
 * - DELETE /consejo?id={id} - Eliminar consejo
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller")
public class ConsejoController {

    /** Servicio para la gestión de operaciones de consejos */
    private ConsejoService consejoService;

    /**
     * Lista todos los consejos disponibles en el sistema.
     * 
     * @return ResponseBase que contiene una lista de todos los consejos
     */
    @GetMapping(value = ApiRoutes.Consejo.LISTAR)
    public ResponseBase<List<ConsejoResponse>> listar() {

        log.info("Inicio request: listar consejos");

        List<ConsejoResponse> listaResponse = consejoService.listar();

        log.info("Fin request: listar consejos. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Crea un nuevo consejo en el sistema.
     * 
     * @param request objeto ConsejoRequest con los datos del consejo a crear
     * @return ResponseBase que contiene el consejo creado con su ID generado
     */
    @PostMapping(value = ApiRoutes.Consejo.GUARDAR)
    public ResponseBase<ConsejoResponse> guardar(@RequestBody ConsejoRequest request) {

        log.info("Inicio request: guardar consejo");
        log.debug("Request guardar consejo: {}", request);

        ConsejoResponse response = consejoService.guardar(request);

        log.info("Fin request: guardar consejo. Id generado: {}", response.getIdConsejo());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina un consejo del sistema por su identificador.
     * 
     * @param id identificador del consejo a eliminar
     * @return ResponseBase con mensaje de confirmación de eliminación
     */
    @DeleteMapping(value = ApiRoutes.Consejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar consejo");
        log.debug("Id consejo a eliminar: {}", id);

        consejoService.eliminar(id);

        log.info("Fin request: eliminar consejo. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza los datos de un consejo existente.
     * 
     * @param id identificador del consejo a actualizar
     * @param request objeto ConsejoRequest con los nuevos datos
     * @return ResponseBase que contiene el consejo actualizado
     */
    @PutMapping(value = ApiRoutes.Consejo.ACTUALIZAR)
    public ResponseBase<ConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ConsejoRequest request) {

        log.info("Inicio request: actualizar consejo");
        log.debug("Id consejo a actualizar: {}", id);
        log.debug("Request actualizar consejo: {}", request);

        ConsejoResponse response = consejoService.actualizar(id, request);

        log.info("Fin request: actualizar consejo. Id actualizado: {}", response.getIdConsejo());

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene un consejo específico por su identificador.
     * 
     * @param id identificador del consejo a recuperar
     * @return ResponseBase que contiene el consejo solicitado
     */
    @GetMapping(value = ApiRoutes.Consejo.OBTENER_POR_ID)
    public ResponseBase<ConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener consejo por id");
        log.debug("Id consejo: {}", id);

        ConsejoResponse response = consejoService.obtenerPorId(id);

        log.info("Fin request: obtener consejo por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca un consejo específico por su nombre.
     * 
     * @param nombre el nombre del consejo a buscar
     * @return ResponseBase que contiene el consejo encontrado
     */
    @GetMapping(value = ApiRoutes.Consejo.BUSCAR_POR_NOMBRE)
    public ResponseBase<ConsejoResponse> buscarPorNombre(@RequestParam String nombre) {

        log.info("Inicio request: buscar consejo por nombre");
        log.debug("Nombre consejo: {}", nombre);

        ConsejoResponse response = consejoService.buscarPorNombre(nombre);

        log.info("Fin request: buscar consejo por nombre");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca todos los consejos que tengan un estado específico.
     * 
     * @param idEstado el identificador del estado a buscar
     * @return ResponseBase que contiene una lista de consejos con el estado especificado
     */
    @GetMapping(value = ApiRoutes.Consejo.BUSCAR_POR_ESTADO)
    public ResponseBase<List<ConsejoResponse>> buscarPorEstado(@RequestParam Integer idEstado) {

        log.info("Inicio request: buscar consejos por estado");
        log.debug("Id estado: {}", idEstado);

        List<ConsejoResponse> listaResponse = consejoService.buscarPorEstado(idEstado);

        log.info(
                "Fin request: buscar consejos por estado. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}