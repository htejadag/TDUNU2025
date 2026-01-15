package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.CatalogoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.CatalogoResponse;
import com.unu.ms.MsSecretariaAcademica.service.CatalogoService;
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
 * Controlador REST encargado de la gestión de catálogos.
 *
 * Expone endpoints para realizar operaciones CRUD y consultas
 * sobre los catálogos del sistema dentro del microservicio.
 * La información de catálogo y auditoría utilizada es propia
 * del dominio de este microservicio.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catalogo Controller")
public class CatalogoController {

    /**
     * Servicio de negocio para la gestión de catálogos.
     */
    CatalogoService catalogoService;

    /**
     * Lista todos los catálogos registrados.
     *
     * @return respuesta con la lista de catálogos
     */
    @GetMapping(value = ApiRoutes.Catalogo.LISTAR)
    public ResponseBase<List<CatalogoResponse>> listar() {

        log.info("Inicio request: listar catalogos");

        List<CatalogoResponse> listaResponse =
                catalogoService.listar();

        log.info("Fin request: listar catalogos. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Registra un nuevo catálogo.
     *
     * @param request datos del catálogo a registrar
     * @return respuesta con el catálogo creado
     */
    @PostMapping(value = ApiRoutes.Catalogo.GUARDAR)
    public ResponseBase<CatalogoResponse> guardar(
            @RequestBody CatalogoRequest request) {

        log.info("Inicio request: guardar catalogo");
        log.debug("Datos de entrada guardar catalogo: {}", request);

        CatalogoResponse response =
                catalogoService.guardar(request);

        log.info("Fin request: guardar catalogo. Id generado: {}", response.getIdCatalogo());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina un catálogo según su identificador.
     *
     * @param id identificador del catálogo a eliminar
     * @return respuesta de confirmación
     */
    @DeleteMapping(value = ApiRoutes.Catalogo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar catalogo");
        log.debug("Id catalogo a eliminar: {}", id);

        catalogoService.eliminar(id);

        log.info("Fin request: eliminar catalogo. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    /**
     * Actualiza la información de un catálogo existente.
     *
     * @param id identificador del catálogo a actualizar
     * @param request datos actualizados del catálogo
     * @return respuesta con el catálogo actualizado
     */
    @PutMapping(value = ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseBase<CatalogoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CatalogoRequest request) {

        log.info("Inicio request: actualizar catalogo");
        log.debug("Id catalogo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar catalogo: {}", request);

        CatalogoResponse response =
                catalogoService.actualizar(id, request);

        log.info("Fin request: actualizar catalogo. Id actualizado: {}", id);

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene un catálogo según su identificador.
     *
     * @param id identificador del catálogo
     * @return respuesta con el catálogo encontrado
     */
    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseBase<CatalogoResponse> obtenerPorId(
            @RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener catalogo por id");
        log.debug("Id catalogo: {}", id);

        CatalogoResponse response =
                catalogoService.obtenerPorId(id);

        log.info("Fin request: obtener catalogo por id. Id: {}", id);

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca un catálogo según su categoría y valor.
     *
     * @param categoria categoría del catálogo
     * @param valor valor del catálogo
     * @return respuesta con el catálogo encontrado
     */
    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA_Y_VALOR)
    public ResponseBase<CatalogoResponse> buscarPorCategoriaYValor(
            @RequestParam String categoria,
            @RequestParam String valor) {

        log.info("Inicio request: buscar catalogo por categoria y valor");
        log.debug("Categoria: {}, Valor: {}", categoria, valor);

        CatalogoResponse response =
                catalogoService.buscarPorCategoriaYValor(categoria, valor);

        log.info("Fin request: buscar catalogo por categoria y valor");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca catálogos asociados a una categoría.
     *
     * @param categoria categoría del catálogo
     * @return respuesta con la lista de catálogos encontrados
     */
    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA)
    public ResponseBase<List<CatalogoResponse>> buscarPorCategoria(
            @RequestParam String categoria) {

        log.info("Inicio request: buscar catalogos por categoria");
        log.debug("Categoria: {}", categoria);

        List<CatalogoResponse> listaResponse =
                catalogoService.buscarPorCategoria(categoria);

        log.info("Fin request: buscar catalogos por categoria. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}