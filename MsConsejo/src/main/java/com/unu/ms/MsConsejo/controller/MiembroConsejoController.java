package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;
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
 * Controlador REST para la gestión de miembros del consejo.
 * 
 * Proporciona endpoints para realizar operaciones CRUD sobre los miembros
 * de los consejos del sistema. Los miembros del consejo son personas que
 * participan en las sesiones y votaciones del consejo.
 * 
 * Endpoints disponibles:
 * - GET /miembro-consejo - Listar todos los miembros
 * - GET /miembro-consejo?id={id} - Obtener miembro por ID
 * - GET /miembro-consejo/consejo?idConsejo={id} - Buscar miembros de un consejo
 * - GET /miembro-consejo/tipo?idTipo={id} - Buscar miembros por tipo
 * - POST /miembro-consejo - Crear nuevo miembro
 * - PUT /miembro-consejo?id={id} - Actualizar miembro
 * - DELETE /miembro-consejo?id={id} - Eliminar miembro
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.MiembroConsejo.BASE)
@Tag(name = "MiembroConsejo Controller")
public class MiembroConsejoController {

    /** Servicio para la gestión de operaciones de miembros del consejo */
    private MiembroConsejoService miembroConsejoService;

    /**
     * Lista todos los miembros del consejo disponibles en el sistema.
     * 
     * @return respuesta base con lista de todos los miembros del consejo
     */
    @GetMapping(value = ApiRoutes.MiembroConsejo.LISTAR)
    public ResponseBase<List<MiembroConsejoResponse>> listar() {

        log.info("Inicio request: listar miembros del consejo");

        List<MiembroConsejoResponse> listaResponse = miembroConsejoService.listar();

        log.info(
                "Fin request: listar miembros del consejo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Crea un nuevo miembro del consejo en el sistema.
     * 
     * @param request datos del miembro a crear (nombre, cargo, consejo, etc.)
     * @return respuesta base con el miembro creado incluyendo su ID generado
     */
    @PostMapping(value = ApiRoutes.MiembroConsejo.GUARDAR)
    public ResponseBase<MiembroConsejoResponse> guardar(@RequestBody MiembroConsejoRequest request) {

        log.info("Inicio request: guardar miembro del consejo");
        log.debug("Request guardar miembro consejo: {}", request);

        MiembroConsejoResponse response = miembroConsejoService.guardar(request);

        log.info(
                "Fin request: guardar miembro del consejo. Id generado: {}",
                response.getIdMiembro()
        );

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina un miembro del consejo del sistema.
     * 
     * @param id identificador del miembro a eliminar
     * @return respuesta base indicando éxito de la eliminación
     */
    @DeleteMapping(value = ApiRoutes.MiembroConsejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar miembro del consejo");
        log.debug("Id miembro consejo a eliminar: {}", id);

        miembroConsejoService.eliminar(id);

        log.info(
                "Fin request: eliminar miembro del consejo. Id eliminado: {}",
                id
        );

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza los datos de un miembro del consejo existente.
     * 
     * @param id identificador del miembro a actualizar
     * @param request nuevos datos del miembro
     * @return respuesta base con el miembro actualizado
     */
    @PutMapping(value = ApiRoutes.MiembroConsejo.ACTUALIZAR)
    public ResponseBase<MiembroConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody MiembroConsejoRequest request) {

        log.info("Inicio request: actualizar miembro del consejo");
        log.debug("Id miembro consejo a actualizar: {}", id);
        log.debug("Request actualizar miembro consejo: {}", request);

        MiembroConsejoResponse response = miembroConsejoService.actualizar(id, request);

        log.info(
                "Fin request: actualizar miembro del consejo. Id actualizado: {}",
                response.getIdMiembro()
        );

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene un miembro del consejo específico por su identificador.
     * 
     * @param id identificador del miembro a obtener
     * @return respuesta base con el miembro encontrado
     */
    @GetMapping(value = ApiRoutes.MiembroConsejo.OBTENER_POR_ID)
    public ResponseBase<MiembroConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener miembro del consejo por id");
        log.debug("Id miembro consejo: {}", id);

        MiembroConsejoResponse response = miembroConsejoService.obtenerPorId(id);

        log.info("Fin request: obtener miembro del consejo por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca todos los miembros que pertenecen a un consejo específico.
     * 
     * @param idConsejo identificador del consejo
     * @return respuesta base con lista de miembros del consejo
     */
    @GetMapping(value = ApiRoutes.MiembroConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {

        log.info("Inicio request: buscar miembros por consejo");
        log.debug("Id consejo: {}", idConsejo);

        List<MiembroConsejoResponse> listaResponse =
                miembroConsejoService.buscarPorConsejo(idConsejo);

        log.info(
                "Fin request: buscar miembros por consejo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca todos los miembros asociados a una persona específica.
     * 
     * @param idPersona identificador de la persona
     * @return respuesta base con lista de membresías de esa persona
     */
    @GetMapping(value = ApiRoutes.MiembroConsejo.BUSCAR_POR_PERSONA)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorPersona(@RequestParam Integer idPersona) {

        log.info("Inicio request: buscar miembros por persona");
        log.debug("Id persona: {}", idPersona);

        List<MiembroConsejoResponse> listaResponse =
                miembroConsejoService.buscarPorPersona(idPersona);

        log.info(
                "Fin request: buscar miembros por persona. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca todos los miembros que ocupan un cargo específico en el consejo.
     * 
     * @param idCargo identificador del cargo
     * @return respuesta base con lista de miembros que ocupan ese cargo
     */
    @GetMapping(value = ApiRoutes.MiembroConsejo.BUSCAR_POR_CARGO)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorCargo(@RequestParam Integer idCargo) {

        log.info("Inicio request: buscar miembros por cargo");
        log.debug("Id cargo: {}", idCargo);

        List<MiembroConsejoResponse> listaResponse =
                miembroConsejoService.buscarPorCargo(idCargo);

        log.info(
                "Fin request: buscar miembros por cargo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
