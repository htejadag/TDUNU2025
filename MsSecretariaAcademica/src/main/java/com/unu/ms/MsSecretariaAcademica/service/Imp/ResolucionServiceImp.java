package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;
import com.unu.ms.MsSecretariaAcademica.model.mapper.ResolucionMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionResponse;
import com.unu.ms.MsSecretariaAcademica.repository.ResolucionRepository;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionService;

/**
 * Implementación del servicio de gestión de resoluciones.
 *
 * Este servicio se encarga de administrar el ciclo de vida de las resoluciones,
 * incluyendo su creación, actualización, eliminación y consulta, así como
 * el registro de auditoría de las acciones realizadas.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ResolucionServiceImp implements ResolucionService {

    /**
     * Repositorio de acceso a datos de resoluciones.
     */
    ResolucionRepository resolucionRepository;

    /**
     * Servicio de auditoría para el registro de acciones sobre resoluciones.
     */
    AuditoriaServiceImp auditoriaServiceImp;

    /**
     * Mapper para la conversión entre entidades y DTOs de resoluciones.
     */
    ResolucionMapper mapper;

    /**
     * Nombre lógico de la entidad utilizada para auditoría.
     */
    private static final String NAME_ENTITY = "RESOLUCION";

    /**
     * Obtiene la lista completa de resoluciones registradas.
     *
     * @return lista de resoluciones
     */
    @Override
    public List<ResolucionResponse> listar() {

        log.info("Inicio servicio: listar resoluciones");

        List<ResolucionResponse> resultado =
                resolucionRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar resoluciones. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene una resolución por su identificador.
     *
     * @param id identificador de la resolución
     * @return resolución encontrada o {@code null} si no existe
     */
    @Override
    public ResolucionResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener resolucion por id");
        log.debug("Id resolucion: {}", id);

        ResolucionResponse response = resolucionRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Resolucion no encontrada. Id: {}", id);
        } else {
            log.info("Resolucion encontrada. Id: {}", id);
        }

        log.info("Fin servicio: obtener resolucion por id");

        return response;
    }

    /**
     * Registra una nueva resolución.
     *
     * La creación de la resolución genera automáticamente
     * un registro de auditoría.
     *
     * @param resolucionRequest datos de la resolución a registrar
     * @return resolución registrada
     */
    @Override
    public ResolucionResponse guardar(ResolucionRequest resolucionRequest) {

        log.info("Inicio servicio: guardar resolucion");
        log.debug("Datos de entrada guardar resolucion: {}", resolucionRequest);

        ResolucionModel model = mapper.toEntity(resolucionRequest);

        ResolucionModel guardado = resolucionRepository.save(model);

        log.info("Resolucion guardada correctamente. Id generado: {}", guardado.getIdResolucion());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdResolucion(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Emisión de resolución");

        log.info("Fin servicio: guardar resolucion");

        return mapper.toResponse(guardado);
    }

    /**
     * Elimina una resolución por su identificador.
     *
     * La eliminación genera un registro de auditoría.
     *
     * @param id identificador de la resolución a eliminar
     */
    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar resolucion");
        log.debug("Id resolucion a eliminar: {}", id);

        ResolucionModel model = resolucionRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Resolucion no encontrada para eliminar. Id: {}", id);
                    return new RuntimeException("Resolucion no encontrada");
                });

        resolucionRepository.deleteById(id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de Resolucion");

        log.info("Fin servicio: eliminar resolucion. Id eliminado: {}", id);
    }

    /**
     * Actualiza una resolución existente.
     *
     * Se registra el estado previo y posterior en la auditoría.
     *
     * @param id identificador de la resolución
     * @param resolucionActualizado datos actualizados de la resolución
     * @return resolución actualizada
     */
    @Override
    public ResolucionResponse actualizar(Integer id, ResolucionRequest resolucionActualizado) {

        log.info("Inicio servicio: actualizar resolucion");
        log.debug("Id resolucion a actualizar: {}", id);
        log.debug("Datos de entrada actualizar resolucion: {}", resolucionActualizado);

        ResolucionModel model = resolucionRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Resolucion no encontrada para actualizar. Id: {}", id);
                    return new RuntimeException("Resolucion no encontrado con id: " + id);
                });

        String antes = model.toString();

        mapper.updateEntityFromRequest(resolucionActualizado, model);

        ResolucionModel actualizado = resolucionRepository.save(model);

        log.info("Resolucion actualizada correctamente. Id: {}", actualizado.getIdResolucion());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdResolucion(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de Resolucion");

        log.info("Fin servicio: actualizar resolucion");

        return mapper.toResponse(actualizado);
    }

    /**
     * Verifica la existencia de una resolución por su identificador.
     *
     * @param id identificador de la resolución
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de resolucion. Id: {}", id);

        boolean existe = resolucionRepository.existsById(id);

        log.debug("Resultado existencia resolucion. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    /**
     * Obtiene las resoluciones asociadas a un expediente.
     *
     * @param idExpediente identificador del expediente
     * @return lista de resoluciones del expediente
     */
    @Override
    public List<ResolucionResponse> buscarPorExpediente(Integer idExpediente) {

        log.info("Inicio servicio: buscar resoluciones por expediente");
        log.debug("Id expediente: {}", idExpediente);

        List<ResolucionResponse> resultado =
                resolucionRepository.findByExpediente_IdExpediente(idExpediente)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resoluciones por expediente. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene una resolución a partir de su número.
     *
     * @param numeroResolucion número de la resolución
     * @return resolución encontrada o {@code null} si no existe
     */
    @Override
    public ResolucionResponse buscarPorNumero(String numeroResolucion) {

        log.info("Inicio servicio: buscar resolucion por numero");
        log.debug("Numero resolucion: {}", numeroResolucion);

        ResolucionResponse response = resolucionRepository.findByNumeroResolucion(numeroResolucion)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Resolucion no encontrada con numero: {}", numeroResolucion);
        }

        log.info("Fin servicio: buscar resolucion por numero");

        return response;
    }

    /**
     * Obtiene resoluciones filtradas por tipo.
     *
     * @param idTipoResolucion identificador del tipo de resolución
     * @return lista de resoluciones del tipo indicado
     */
    @Override
    public List<ResolucionResponse> buscarPorTipo(Integer idTipoResolucion) {

        log.info("Inicio servicio: buscar resoluciones por tipo");
        log.debug("Id tipo resolucion: {}", idTipoResolucion);

        List<ResolucionResponse> resultado =
                resolucionRepository.findByIdTipoResolucion(idTipoResolucion)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resoluciones por tipo. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene resoluciones filtradas por estado.
     *
     * @param idEstado identificador del estado
     * @return lista de resoluciones con el estado indicado
     */
    @Override
    public List<ResolucionResponse> buscarPorEstado(Integer idEstado) {

        log.info("Inicio servicio: buscar resoluciones por estado");
        log.debug("Id estado: {}", idEstado);

        List<ResolucionResponse> resultado =
                resolucionRepository.findByIdEstado(idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resoluciones por estado. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene resoluciones filtradas por tipo y estado.
     *
     * @param idTipoResolucion identificador del tipo de resolución
     * @param idEstado identificador del estado
     * @return lista de resoluciones que cumplen ambos criterios
     */
    @Override
    public List<ResolucionResponse> buscarPorTipoYEstado(Integer idTipoResolucion, Integer idEstado) {

        log.info("Inicio servicio: buscar resoluciones por tipo y estado");
        log.debug("Id tipo resolucion: {}, Id estado: {}", idTipoResolucion, idEstado);

        List<ResolucionResponse> resultado =
                resolucionRepository
                        .findByIdTipoResolucionAndIdEstado(idTipoResolucion, idEstado)
                        .stream()
                        .filter(resolucion -> resolucion.getIdEstado().equals(idEstado))
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resoluciones por tipo y estado. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene resoluciones filtradas por aprobación en sesión.
     *
     * @param aprobadoEnSesion indicador de aprobación en sesión
     * @return lista de resoluciones aprobadas o no aprobadas en sesión
     */
    @Override
    public List<ResolucionResponse> buscarPorAprobadoEnSesion(Integer aprobadoEnSesion) {

        log.info("Inicio servicio: buscar resoluciones por aprobado en sesion");
        log.debug("Aprobado en sesion: {}", aprobadoEnSesion);

        List<ResolucionResponse> resultado =
                resolucionRepository.findByAprobadoEnSesion(aprobadoEnSesion)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resoluciones por aprobado en sesion. Total registros: {}", resultado.size());

        return resultado;
    }

}