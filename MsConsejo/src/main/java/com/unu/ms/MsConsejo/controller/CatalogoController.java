package com.unu.ms.MsConsejo.controller;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.CatalogoRequest;
import com.unu.ms.MsConsejo.model.response.CatalogoResponse;
import com.unu.ms.MsConsejo.service.CatalogoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

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
 * Controlador REST para la gestión de catálogos.
 * 
 * Proporciona endpoints para realizar operaciones CRUD (Create, Read, Update, Delete)
 * sobre los catálogos del sistema. Los catálogos son valores dinámicos organizados
 * por categorías que se utilizan en toda la aplicación.
 * 
 * Endpoints disponibles:
 * - GET /catalogo - Listar todos los catálogos
 * - GET /catalogo?id={id} - Obtener catálogo por ID
 * - GET /catalogo/categoria?categoria={categoria} - Buscar catálogos por categoría
 * - GET /catalogo/categoria-valor?categoria={cat}&valor={val} - Buscar por categoría y valor
 * - POST /catalogo - Crear nuevo catálogo
 * - PUT /catalogo?id={id} - Actualizar catálogo existente
 * - DELETE /catalogo?id={id} - Eliminar catálogo
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catalogo Controller")
public class CatalogoController {

    /** Servicio para la gestión de operaciones de catálogos */
    private CatalogoService catalogoService;

    /**
     * Lista todos los catálogos disponibles en el sistema.
     * 
     * @return ResponseBase que contiene una lista de todos los catálogos disponibles
     */
    @GetMapping(value = ApiRoutes.Catalogo.LISTAR)
    public ResponseBase<List<CatalogoResponse>> listar() {

        log.info("Inicio request: listar catalogos");

        List<CatalogoResponse> listaResponse = catalogoService.listar();

        log.info("Fin request: listar catalogos. Total registros: {}", listaResponse.size());
       

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    /**
     * Crea un nuevo catálogo en el sistema.
     * 
     * @param request objeto CatalogoRequest con los datos del catálogo a crear
     * @return ResponseBase que contiene el catálogo creado con su ID generado
     */
    @PostMapping(value = ApiRoutes.Catalogo.GUARDAR)
    public ResponseBase<CatalogoResponse> guardar(@RequestBody CatalogoRequest request) {

        log.info("Inicio request: guardar catalogo");
        log.debug("Request guardar catalogo: {}", request);

        CatalogoResponse response = catalogoService.guardar(request);

        log.info("Fin request: guardar catalogo. Id generado: {}", response.getIdCatalogo());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    /**
     * Elimina un catálogo del sistema por su identificador.
     * 
     * @param id identificador del catálogo a eliminar
     * @return ResponseBase con mensaje de confirmación de eliminación
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
     * Actualiza los datos de un catálogo existente.
     * 
     * @param id identificador del catálogo a actualizar
     * @param request objeto CatalogoRequest con los nuevos datos
     * @return ResponseBase que contiene el catálogo actualizado
     */
    @PutMapping(value = ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseBase<CatalogoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CatalogoRequest request) {

        log.info("Inicio request: actualizar catalogo");
        log.debug("Id catalogo a actualizar: {}", id);
        log.debug("Request actualizar catalogo: {}", request);

        CatalogoResponse response = catalogoService.actualizar(id, request);

        log.info("Fin request: actualizar catalogo. Id actualizado: {}", response.getIdCatalogo());

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    /**
     * Obtiene un catálogo específico por su identificador.
     * 
     * @param id identificador del catálogo a recuperar
     * @return ResponseBase que contiene el catálogo solicitado
     */
    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseBase<CatalogoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener catalogo por id");
        log.debug("Id catalogo: {}", id);

        CatalogoResponse response = catalogoService.obtenerPorId(id);

        log.info("Fin request: obtener catalogo por id");


        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    /**
     * Busca un catálogo específico por su categoría y valor.
     * 
     * @param categoria la categoría del catálogo a buscar
     * @param valor el valor del catálogo a buscar
     * @return ResponseBase que contiene el catálogo encontrado
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
     * Busca todos los catálogos pertenecientes a una categoría específica.
     * 
     * @param categoria la categoría de los catálogos a buscar
     * @return ResponseBase que contiene una lista de catálogos de la categoría especificada
     */
    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA)
    public ResponseBase<List<CatalogoResponse>> buscarPorCategoria(@RequestParam String categoria) {

        log.info("Inicio request: buscar catalogos por categoria");
        log.debug("Categoria: {}", categoria);

        List<CatalogoResponse> listaResponse =
                catalogoService.buscarPorCategoria(categoria);

        log.info(
                "Fin request: buscar catalogos por categoria. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}