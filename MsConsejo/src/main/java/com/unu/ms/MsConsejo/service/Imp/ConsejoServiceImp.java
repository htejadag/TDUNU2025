package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.ConsejoMapper;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;

/**
 * Implementación del servicio para la gestión de consejos.
 * 
 * Proporciona la lógica de negocio para las operaciones CRUD sobre consejos,
 * incluyendo búsquedas por nombre y estado. Registra auditoría para todas
 * las operaciones de creación, actualización y eliminación.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConsejoServiceImp implements ConsejoService {

    /** Repositorio para acceso a datos de consejos */
    private ConsejoRepository consejoRepository;
    
    /** Servicio de auditoría para registrar cambios */
    private AuditoriaServiceImp auditoriaServiceImp;
    
    /** Mapeador para convertir entre entidades y DTOs */
    private ConsejoMapper mapper;

    /** Nombre de la entidad para auditoría */
    private static final String NAME_ENTITY = "CONSEJO";

    /**
     * Lista todos los consejos disponibles en el sistema.
     * 
     * @return lista completa de consejos convertidos a ConsejoResponse
     */
    @Override
    public List<ConsejoResponse> listar() {

        log.info("Inicio servicio: listar consejos");

        List<ConsejoResponse> resultado =
                consejoRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar consejos. Total registros: {}", resultado.size());

        return resultado;
    }

    /**
     * Obtiene un consejo específico por su identificador.
     * 
     * @param id identificador del consejo a obtener
     * @return el consejo encontrado, o null si no existe
     */
    @Override
    public ConsejoResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener consejo por id");
        log.debug("Id consejo: {}", id);

        ConsejoResponse response = consejoRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Consejo no encontrado. Id: {}", id);
        } else {
            log.info("Consejo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener consejo por id");

        return response;
    }

    /**
     * Crea un nuevo consejo en el sistema.
     * Registra la acción en la auditoría.
     * 
     * @param consejoRequest datos del consejo a crear
     * @return el consejo creado con su identificador generado
     */
    @Override
    public ConsejoResponse guardar(ConsejoRequest consejoRequest) {

        log.info("Inicio servicio: guardar consejo");
        log.debug("Datos de entrada guardar consejo: {}", consejoRequest);

        ConsejoModel model = mapper.toEntity(consejoRequest);
        ConsejoModel guardado = consejoRepository.save(model);

        log.info("Consejo guardado correctamente. Id generado: {}", guardado.getIdConsejo());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdConsejo(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Registro de nuevo Consejo"
        );

        log.info("Fin servicio: guardar consejo");

        return mapper.toResponse(guardado);
    }

    /**
     * Elimina un consejo del sistema por su identificador.
     * Registra la acción en la auditoría.
     * 
     * @param id identificador del consejo a eliminar
     * @throws RuntimeException si el consejo no existe
     */
    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar consejo");
        log.debug("Id consejo a eliminar: {}", id);

        ConsejoModel model = consejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Consejo no encontrado para eliminar. Id: {}", id);
                    return new RuntimeException("Consejo no encontrada");
                });

        consejoRepository.deleteById(id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de Consejo"
        );

        log.info("Fin servicio: eliminar consejo. Id eliminado: {}", id);
    }

    /**
     * Actualiza los datos de un consejo existente.
     * Registra la acción en la auditoría con los datos antes y después.
     * 
     * @param id identificador del consejo a actualizar
     * @param consejoActualizado nuevos datos del consejo
     * @return el consejo actualizado
     * @throws RuntimeException si el consejo no existe
     */
    @Override
    public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado) {

        log.info("Inicio servicio: actualizar consejo");
        log.debug("Id consejo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar consejo: {}", consejoActualizado);

        ConsejoModel model = consejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Consejo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Consejo no encontrado con id: " + id);
                });

        String antes = model.toString();

        mapper.updateEntityFromRequest(consejoActualizado, model);

        ConsejoModel actualizado = consejoRepository.save(model);

        log.info("Consejo actualizado correctamente. Id: {}", actualizado.getIdConsejo());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdConsejo(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de Consejo"
        );

        log.info("Fin servicio: actualizar consejo");

        return mapper.toResponse(actualizado);
    }

    /**
     * Verifica si un consejo existe en el sistema.
     * 
     * @param id identificador del consejo a verificar
     * @return true si el consejo existe, false en caso contrario
     */
    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de consejo. Id: {}", id);

        boolean existe = consejoRepository.existsById(id);

        log.debug("Resultado existencia consejo. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    /**
     * Busca un consejo específico por su nombre.
     * 
     * @param nombre el nombre del consejo a buscar
     * @return el consejo encontrado, o null si no existe
     */
    @Override
    public ConsejoResponse buscarPorNombre(String nombre) {

        log.info("Inicio servicio: buscar consejo por nombre");
        log.debug("Nombre consejo: {}", nombre);

        ConsejoResponse response = consejoRepository.findByNombre(nombre)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Consejo no encontrado con nombre: {}", nombre);
        }

        log.info("Fin servicio: buscar consejo por nombre");

        return response;
    }

    /**
     * Busca todos los consejos que tengan un estado específico.
     * 
     * @param idEstado el identificador del estado a buscar
     * @return lista de consejos con el estado especificado
     */
    @Override
    public List<ConsejoResponse> buscarPorEstado(Integer idEstado) {

        log.info("Inicio servicio: buscar consejos por estado");
        log.debug("Id estado: {}", idEstado);

        List<ConsejoResponse> resultado =
                consejoRepository.findByIdEstado(idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar consejos por estado. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}