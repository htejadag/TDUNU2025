package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionResponse;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionService;
import com.unu.ms.MsSecretariaAcademica.util.ApiRoutes;
import com.unu.ms.MsSecretariaAcademica.util.Mensajes;
import com.unu.ms.MsSecretariaAcademica.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Controlador REST encargado de la gestión de resoluciones.
 *
 * Expone endpoints para realizar operaciones CRUD y consultas
 * sobre resoluciones dentro del microservicio. La información
 * relacionada a catálogos y auditoría forma parte del propio
 * dominio del microservicio.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Resolucion.BASE)
@Tag(name = "Resolucion Controller")
public class ResolucionController {

    /**
     * Servicio de negocio para la gestión de resoluciones.
     */
    ResolucionService resolucionService;

    /**
     * Lista todas las resoluciones registradas.
     *
     * @return respuesta con la lista de resoluciones
     */
    @GetMapping(value = ApiRoutes.Resolucion.LISTAR)
    public ResponseBase<List<ResolucionResponse>> listar() {

        log.info("Inicio request: listar resoluciones");

        List<ResolucionResponse> listaResponse = resolucionService.listar();

        log.info("Fin request: listar resoluciones. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Registra una nueva resolución.
     *
     * @param request datos de la resolución a registrar
     * @return respuesta con la resolución creada
     */
    @PostMapping(value = ApiRoutes.Resolucion.GUARDAR)
    public ResponseBase<ResolucionResponse> guardar(@RequestBody ResolucionRequest request) {

        log.info("Inicio request: guardar resolucion");
        log.debug("Datos de entrada guardar resolucion: {}", request);

        ResolucionResponse response = resolucionService.guardar(request);

        log.info("Fin request: guardar resolucion. Id generado: {}", response.getIdResolucion());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina una resolución según su identificador.
     *
     * @param id identificador de la resolución a eliminar
     * @return respuesta de confirmación
     */
    @DeleteMapping(value = ApiRoutes.Resolucion.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar resolucion");
        log.debug("Id resolucion a eliminar: {}", id);

        resolucionService.eliminar(id);

        log.info("Fin request: eliminar resolucion. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza la información de una resolución existente.
     *
     * @param id identificador de la resolución a actualizar
     * @param request datos actualizados de la resolución
     * @return respuesta con la resolución actualizada
     */
    @PutMapping(value = ApiRoutes.Resolucion.ACTUALIZAR)
    public ResponseBase<ResolucionResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ResolucionRequest request) {

        log.info("Inicio request: actualizar resolucion");
        log.debug("Id resolucion a actualizar: {}", id);
        log.debug("Datos de entrada actualizar resolucion: {}", request);

        ResolucionResponse response = resolucionService.actualizar(id, request);

        log.info("Fin request: actualizar resolucion. Id actualizado: {}", id);

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene una resolución según su identificador.
     *
     * @param id identificador de la resolución
     * @return respuesta con la resolución encontrada
     */
    @GetMapping(value = ApiRoutes.Resolucion.OBTENER_POR_ID)
    public ResponseBase<ResolucionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener resolucion por id");
        log.debug("Id resolucion: {}", id);

        ResolucionResponse response = resolucionService.obtenerPorId(id);

        log.info("Fin request: obtener resolucion por id. Id: {}", id);

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca resoluciones asociadas a un expediente.
     *
     * @param idExpediente identificador del expediente
     * @return respuesta con la lista de resoluciones encontradas
     */
    @GetMapping(value = ApiRoutes.Resolucion.BUSCAR_POR_EXPEDIENTE)
    public ResponseBase<List<ResolucionResponse>> buscarPorExpediente(@RequestParam Integer idExpediente) {

        log.info("Inicio request: buscar resoluciones por expediente");
        log.debug("Id expediente: {}", idExpediente);

        List<ResolucionResponse> listaResponse =
                resolucionService.buscarPorExpediente(idExpediente);

        log.info("Fin request: buscar resoluciones por expediente. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca una resolución por su número.
     *
     * @param numero número de la resolución
     * @return respuesta con la resolución encontrada
     */
    @GetMapping(value = ApiRoutes.Resolucion.BUSCAR_POR_NUMERO)
    public ResponseBase<ResolucionResponse> buscarPorNumero(@RequestParam String numero) {

        log.info("Inicio request: buscar resolucion por numero");
        log.debug("Numero resolucion: {}", numero);

        ResolucionResponse response = resolucionService.buscarPorNumero(numero);

        log.info("Fin request: buscar resolucion por numero");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca resoluciones según su tipo.
     *
     * @param idTipoResolucion identificador del tipo de resolución
     * @return respuesta con la lista de resoluciones encontradas
     */
    @GetMapping(value = ApiRoutes.Resolucion.BUSCAR_POR_TIPO)
    public ResponseBase<List<ResolucionResponse>> buscarPorTipo(@RequestParam Integer idTipoResolucion) {

        log.info("Inicio request: buscar resoluciones por tipo");
        log.debug("Id tipo resolucion: {}", idTipoResolucion);

        List<ResolucionResponse> listaResponse =
                resolucionService.buscarPorTipo(idTipoResolucion);

        log.info("Fin request: buscar resoluciones por tipo. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca resoluciones según su estado.
     *
     * @param idEstado identificador del estado de la resolución
     * @return respuesta con la lista de resoluciones encontradas
     */
    @GetMapping(value = ApiRoutes.Resolucion.BUSCAR_POR_ESTADO)
    public ResponseBase<List<ResolucionResponse>> buscarPorEstado(@RequestParam Integer idEstado) {

        log.info("Inicio request: buscar resoluciones por estado");
        log.debug("Id estado: {}", idEstado);

        List<ResolucionResponse> listaResponse =
                resolucionService.buscarPorEstado(idEstado);

        log.info("Fin request: buscar resoluciones por estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca resoluciones según su tipo y estado.
     *
     * @param idTipoResolucion identificador del tipo de resolución
     * @param idEstado identificador del estado de la resolución
     * @return respuesta con la lista de resoluciones encontradas
     */
    @GetMapping(value = ApiRoutes.Resolucion.BUSCAR_POR_TIPO_Y_ESTADO)
    public ResponseBase<List<ResolucionResponse>> buscarPorTipoYEstado(
            @RequestParam Integer idTipoResolucion,
            @RequestParam Integer idEstado) {

        log.info("Inicio request: buscar resoluciones por tipo y estado");
        log.debug("Id tipo resolucion: {}, Id estado: {}", idTipoResolucion, idEstado);

        List<ResolucionResponse> listaResponse =
                resolucionService.buscarPorTipoYEstado(idTipoResolucion, idEstado);

        log.info("Fin request: buscar resoluciones por tipo y estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Busca resoluciones según si fueron aprobadas en sesión.
     *
     * @param aprobadoEnSesion indicador de aprobación en sesión
     * @return respuesta con la lista de resoluciones encontradas
     */
    @GetMapping(value = ApiRoutes.Resolucion.BUSCAR_POR_APROBADO_EN_SESION)
    public ResponseBase<List<ResolucionResponse>> buscarPorAprobadoEnSesion(
            @RequestParam Integer aprobadoEnSesion) {

        log.info("Inicio request: buscar resoluciones por aprobado en sesion");
        log.debug("Aprobado en sesion: {}", aprobadoEnSesion);

        List<ResolucionResponse> listaResponse =
                resolucionService.buscarPorAprobadoEnSesion(aprobadoEnSesion);

        log.info("Fin request: buscar resoluciones por aprobado en sesion. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
