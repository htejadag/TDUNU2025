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
 * Expone endpoints para realizar operaciones CRUD (Create, Read, Update, Delete)
 * y consultas especializadas sobre los catálogos del sistema dentro del microservicio
 * de Secretaría Académica. Todos los endpoints retornan respuestas envueltas en
 * objetos {@link ResponseBase} que incluyen código de estado, mensajes y datos.
 *
 * La información de catálogo y auditoría utilizada es propia del dominio
 * de este microservicio y se integra con los servicios de negocio correspondientes.
 *
 * Endpoints disponibles:
 * - GET: Listar todos los catálogos
 * - GET: Obtener catálogo por identificador
 * - GET: Buscar catálogos por categoría
 * - GET: Buscar catálogo por categoría y valor
 * - POST: Crear nuevo catálogo
 * - PUT: Actualizar catálogo existente
 * - DELETE: Eliminar catálogo
 *
 * @author Equipo de Desarrollo - Secretaría Académica
 * @version 1.0
 * @since 2026-01-15
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catalogo Controller")
public class CatalogoController {

    /**
     * Servicio de negocio para la gestión de catálogos.
     * Inyectado automáticamente por el contenedor de Spring mediante
     * la anotación {@link AllArgsConstructor}. Proporciona acceso a todas
     * las operaciones de negocio relacionadas con catálogos.
     * 
     * @see CatalogoService
     */
    private final CatalogoService catalogoService;

    /**
     * Obtiene la lista completa de todos los catálogos registrados en el sistema.
     *
     * Este endpoint realiza una consulta a la base de datos para recuperar
     * todos los registros de catálogos sin ningún filtro. La operación está
     * registrada mediante logs a nivel informativo para auditoría.
     *
     * HTTP: GET
     * Ruta: /api/catalogos/listar
     *
     * @return {@link ResponseBase} con la lista de {@link CatalogoResponse},
     *         incluyendo código de éxito y mensaje descriptivo
     * @throws Exception si ocurre un error en la base de datos o servicio
     *
     * @see CatalogoService#listar()
     * @see ResponseBase
     * @see CatalogoResponse
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
     * Crea y registra un nuevo catálogo en el sistema.
     *
     * Este endpoint recibe los datos del catálogo mediante el body de la solicitud,
     * valida la información y genera un nuevo registro en la base de datos.
     * Retorna el catálogo creado con su identificador generado automáticamente.
     *
     * HTTP: POST
     * Ruta: /api/catalogos/guardar
     * Content-Type: application/json
     *
     * @param request {@link CatalogoRequest} con los datos del catálogo a crear.
     *                Debe contener categoría, valor y descripción
     * @return {@link ResponseBase} con el {@link CatalogoResponse} creado,
     *         incluyendo el identificador generado
     * @throws IllegalArgumentException si el request es inválido o incompleto
     * @throws Exception si ocurre un error en la base de datos
     *
     * @see CatalogoService#guardar(CatalogoRequest)
     * @see CatalogoRequest
     * @see CatalogoResponse
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
     * Elimina un catálogo del sistema según su identificador único.
     *
     * Este endpoint recibe el identificador del catálogo como parámetro de consulta
     * y procede a su eliminación lógica o física de la base de datos.
     * Retorna un mensaje de confirmación si la operación es exitosa.
     *
     * HTTP: DELETE
     * Ruta: /api/catalogos/eliminar?id={id}
     *
     * @param id identificador único del catálogo a eliminar. Debe ser un valor
     *           positivo y existente en la base de datos
     * @return {@link ResponseBase} con mensaje de confirmación de eliminación
     * @throws EntityNotFoundException si el catálogo con el id especificado no existe
     * @throws Exception si ocurre un error en la base de datos
     *
     * @see CatalogoService#eliminar(Integer)
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
     * Actualiza los datos de un catálogo existente en el sistema.
     *
     * Este endpoint permite modificar la información de un catálogo específico.
     * El identificador se proporciona como parámetro de consulta, mientras que
     * los datos actualizados se envían en el body de la solicitud.
     * Solo se modifican los campos proporcionados en el request.
     *
     * HTTP: PUT
     * Ruta: /api/catalogos/actualizar?id={id}
     * Content-Type: application/json
     *
     * @param id identificador único del catálogo a actualizar
     * @param request {@link CatalogoRequest} con los datos actualizados del catálogo
     * @return {@link ResponseBase} con el {@link CatalogoResponse} actualizado
     * @throws EntityNotFoundException si el catálogo con el id especificado no existe
     * @throws IllegalArgumentException si el request es inválido
     * @throws Exception si ocurre un error en la base de datos
     *
     * @see CatalogoService#actualizar(Integer, CatalogoRequest)
     * @see CatalogoRequest
     * @see CatalogoResponse
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
     * Recupera un catálogo específico del sistema según su identificador único.
     *
     * Este endpoint busca y retorna un catálogo individual basándose en
     * su identificador. Si el catálogo existe, se retorna en el body de la respuesta.
     * Si no existe, se lanza una excepción de entidad no encontrada.
     *
     * HTTP: GET
     * Ruta: /api/catalogos/obtener?id={id}
     *
     * @param id identificador único del catálogo a recuperar. Debe ser un valor
     *           positivo y existente en la base de datos
     * @return {@link ResponseBase} con el {@link CatalogoResponse} encontrado
     * @throws EntityNotFoundException si el catálogo con el id especificado no existe
     * @throws Exception si ocurre un error en la base de datos
     *
     * @see CatalogoService#obtenerPorId(Integer)
     * @see CatalogoResponse
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
     * Busca y retorna un catálogo específico mediante su categoría y valor.
     *
     * Este endpoint realiza una búsqueda combinada utilizando dos criterios:
     * la categoría y el valor del catálogo. Esta búsqueda es particularmente útil
     * cuando se necesita localizar un catálogo específico conociendo ambos parámetros.
     * La búsqueda es sensible a mayúsculas/minúsculas según la configuración de la BD.
     *
     * HTTP: GET
     * Ruta: /api/catalogos/buscar?categoria={cat}&valor={val}
     *
     * @param categoria categoría del catálogo a buscar. Parámetro obligatorio
     *                  que debe coincidir exactamente con el valor almacenado
     * @param valor valor del catálogo a buscar. Parámetro obligatorio que
     *              identifica el valor específico dentro de la categoría
     * @return {@link ResponseBase} con el {@link CatalogoResponse} encontrado
     * @throws EntityNotFoundException si no existe un catálogo con la categoría
     *                                   y valor especificados
     * @throws Exception si ocurre un error en la base de datos
     *
     * @see CatalogoService#buscarPorCategoriaYValor(String, String)
     * @see CatalogoResponse
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
     * Recupera todos los catálogos que pertenecen a una categoría específica.
     *
     * Este endpoint realiza una búsqueda filtrada por categoría, retornando
     * todos los catálogos que coincidan con la categoría proporcionada.
     * Es útil para obtener todas las opciones disponibles dentro de una
     * categoría específica (ej: "ESTADO", "TIPO_DOCUMENTO", etc.).
     *
     * HTTP: GET
     * Ruta: /api/catalogos/buscar-por-categoria?categoria={cat}
     *
     * @param categoria categoría por la cual filtrar los catálogos.
     *                  Parámetro obligatorio que debe coincidir exactamente
     *                  con los valores almacenados en la base de datos
     * @return {@link ResponseBase} con lista de {@link CatalogoResponse}
     *         que pertenecen a la categoría especificada. Puede ser una lista vacía
     *         si no hay catálogos en esa categoría
     * @throws Exception si ocurre un error en la base de datos
     *
     * @see CatalogoService#buscarPorCategoria(String)
     * @see CatalogoResponse
     * @see ResponseBase
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