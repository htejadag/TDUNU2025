package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionArticuloService;
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
 * Controlador REST encargado de la gestión de artículos de resoluciones.
 *
 * Expone endpoints para realizar operaciones CRUD y consultas
 * sobre los artículos asociados a una resolución dentro del
 * microservicio. La información de catálogo y auditoría utilizada
 * es propia del dominio del microservicio.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.ResolucionArticulo.BASE)
@Tag(name = "ResolucionArticulo Controller")
public class ResolucionArticuloController {

    /**
     * Servicio de negocio para la gestión de artículos de resoluciones.
     */
    ResolucionArticuloService resolucionArticuloService;

    /**
     * Lista todos los artículos de resoluciones registrados.
     *
     * @return respuesta con la lista de artículos de resoluciones
     */
    @GetMapping(value = ApiRoutes.ResolucionArticulo.LISTAR)
    public ResponseBase<List<ResolucionArticuloResponse>> listar() {

        log.info("Inicio request: listar resolucion articulos");

        List<ResolucionArticuloResponse> listaResponse =
                resolucionArticuloService.listar();

        log.info("Fin request: listar resolucion articulos. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Registra un nuevo artículo de resolución.
     *
     * @param request datos del artículo de la resolución a registrar
     * @return respuesta con el artículo de resolución creado
     */
    @PostMapping(value = ApiRoutes.ResolucionArticulo.GUARDAR)
    public ResponseBase<ResolucionArticuloResponse> guardar(
            @RequestBody ResolucionArticuloRequest request) {

        log.info("Inicio request: guardar resolucion articulo");
        log.debug("Datos de entrada guardar resolucion articulo: {}", request);

        ResolucionArticuloResponse response =
                resolucionArticuloService.guardar(request);

        log.info("Fin request: guardar resolucion articulo. Id generado: {}", response.getIdArticulo());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina un artículo de resolución según su identificador.
     *
     * @param id identificador del artículo de resolución a eliminar
     * @return respuesta de confirmación
     */
    @DeleteMapping(value = ApiRoutes.ResolucionArticulo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar resolucion articulo");
        log.debug("Id resolucion articulo a eliminar: {}", id);

        resolucionArticuloService.eliminar(id);

        log.info("Fin request: eliminar resolucion articulo. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza la información de un artículo de resolución existente.
     *
     * @param id identificador del artículo de resolución a actualizar
     * @param request datos actualizados del artículo de resolución
     * @return respuesta con el artículo de resolución actualizado
     */
    @PutMapping(value = ApiRoutes.ResolucionArticulo.ACTUALIZAR)
    public ResponseBase<ResolucionArticuloResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ResolucionArticuloRequest request) {

        log.info("Inicio request: actualizar resolucion articulo");
        log.debug("Id resolucion articulo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar resolucion articulo: {}", request);

        ResolucionArticuloResponse response =
                resolucionArticuloService.actualizar(id, request);

        log.info("Fin request: actualizar resolucion articulo. Id actualizado: {}", id);

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene un artículo de resolución según su identificador.
     *
     * @param id identificador del artículo de resolución
     * @return respuesta con el artículo de resolución encontrado
     */
    @GetMapping(value = ApiRoutes.ResolucionArticulo.OBTENER_POR_ID)
    public ResponseBase<ResolucionArticuloResponse> obtenerPorId(
            @RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener resolucion articulo por id");
        log.debug("Id resolucion articulo: {}", id);

        ResolucionArticuloResponse response =
                resolucionArticuloService.obtenerPorId(id);

        log.info("Fin request: obtener resolucion articulo por id. Id: {}", id);

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca artículos de resoluciones asociados a una resolución.
     *
     * @param idResolucion identificador de la resolución
     * @return respuesta con la lista de artículos de resoluciones encontrados
     */
    @GetMapping(value = ApiRoutes.ResolucionArticulo.BUSCAR_POR_RESOLUCION)
    public ResponseBase<List<ResolucionArticuloResponse>> buscarPorResolucion(
            @RequestParam Integer idResolucion) {

        log.info("Inicio request: buscar resolucion articulos por resolucion");
        log.debug("Id resolucion: {}", idResolucion);

        List<ResolucionArticuloResponse> listaResponse =
                resolucionArticuloService.buscarPorResolucion(idResolucion);

        log.info("Fin request: buscar resolucion articulos por resolucion. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
