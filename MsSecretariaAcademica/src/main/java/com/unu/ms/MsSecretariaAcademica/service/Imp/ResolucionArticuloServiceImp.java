package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionArticuloModel;
import com.unu.ms.MsSecretariaAcademica.model.mapper.ResolucionArticuloMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsSecretariaAcademica.repository.ResolucionArticuloRepository;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionArticuloService;

/**
 * Implementación del servicio de gestión de artículos de resoluciones.
 *
 * Este servicio se encarga de administrar los artículos asociados
 * a una resolución, permitiendo su creación, actualización,
 * eliminación y consulta.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ResolucionArticuloServiceImp implements ResolucionArticuloService {

    /**
     * Repositorio de acceso a datos de artículos de resolución.
     */
    ResolucionArticuloRepository resolucionArticuloRepository;

    /**
     * Mapper para la conversión entre entidades y DTOs de artículos de resolución.
     */
    ResolucionArticuloMapper mapper;

    /**
     * Obtiene la lista completa de artículos de resoluciones.
     *
     * @return lista de artículos de resolución
     */
    @Override
    public List<ResolucionArticuloResponse> listar() {

        log.info("Inicio servicio: listar resolucion articulos");

        List<ResolucionArticuloResponse> resultado =
                resolucionArticuloRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar resolucion articulos. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene un artículo de resolución por su identificador.
     *
     * @param id identificador del artículo
     * @return artículo encontrado o {@code null} si no existe
     */
    @Override
    public ResolucionArticuloResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener resolucion articulo por id");
        log.debug("Id resolucion articulo: {}", id);

        ResolucionArticuloResponse response = resolucionArticuloRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Resolucion articulo no encontrado. Id: {}", id);
        } else {
            log.info("Resolucion articulo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener resolucion articulo por id");

        return response;
    }

    /**
     * Registra un nuevo artículo de resolución.
     *
     * @param resolucionArticuloRequest datos del artículo a registrar
     * @return artículo registrado
     */
    @Override
    public ResolucionArticuloResponse guardar(ResolucionArticuloRequest resolucionArticuloRequest) {

        log.info("Inicio servicio: guardar resolucion articulo");
        log.debug("Datos de entrada guardar resolucion articulo: {}", resolucionArticuloRequest);

        ResolucionArticuloModel model = mapper.toEntity(resolucionArticuloRequest);

        log.debug("Entidad resolucion articulo mapeada correctamente");

        ResolucionArticuloModel guardado = resolucionArticuloRepository.save(model);

        log.info("Resolucion articulo guardado correctamente. Id generado: {}", guardado.getIdArticulo());

        return mapper.toResponse(guardado);
    }

    /**
     * Elimina un artículo de resolución por su identificador.
     *
     * @param id identificador del artículo a eliminar
     */
    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar resolucion articulo");
        log.debug("Id resolucion articulo a eliminar: {}", id);

        resolucionArticuloRepository.deleteById(id);

        log.info("Fin servicio: eliminar resolucion articulo. Id eliminado: {}", id);
    }

    /**
     * Actualiza un artículo de resolución existente.
     *
     * @param id identificador del artículo a actualizar
     * @param resolucionArticuloActualizado datos actualizados del artículo
     * @return artículo actualizado
     */
    @Override
    public ResolucionArticuloResponse actualizar(
            Integer id,
            ResolucionArticuloRequest resolucionArticuloActualizado
    ) {

        log.info("Inicio servicio: actualizar resolucion articulo");
        log.debug("Id resolucion articulo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar resolucion articulo: {}", resolucionArticuloActualizado);

        ResolucionArticuloModel model = resolucionArticuloRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Resolucion articulo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Resolucion Articulo no encontrado con id: " + id);
                });

        log.debug("Resolucion articulo encontrado. Aplicando cambios");

        mapper.updateEntityFromRequest(resolucionArticuloActualizado, model);

        ResolucionArticuloModel actualizado = resolucionArticuloRepository.save(model);

        log.info("Resolucion articulo actualizado correctamente. Id: {}", actualizado.getIdArticulo());

        return mapper.toResponse(actualizado);
    }

    /**
     * Verifica la existencia de un artículo de resolución por su identificador.
     *
     * @param id identificador del artículo
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de resolucion articulo. Id: {}", id);

        boolean existe = resolucionArticuloRepository.existsById(id);

        log.debug("Resultado existencia resolucion articulo. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    /**
     * Obtiene la lista de artículos asociados a una resolución,
     * ordenados ascendentemente por número de artículo.
     *
     * @param idResolucion identificador de la resolución
     * @return lista de artículos de la resolución
     */
    @Override
    public List<ResolucionArticuloResponse> buscarPorResolucion(Integer idResolucion) {

        log.info("Inicio servicio: buscar resolucion articulos por resolucion");
        log.debug("Id resolucion: {}", idResolucion);

        List<ResolucionArticuloResponse> resultado =
                resolucionArticuloRepository
                        .findByResolucion_IdResolucionOrderByNumeroArticuloAsc(idResolucion)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resolucion articulos por resolucion. Total registros: {}", resultado.size());

        return resultado;
    }

}
