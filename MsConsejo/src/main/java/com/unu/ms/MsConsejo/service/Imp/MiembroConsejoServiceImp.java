package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.MiembroConsejoMapper;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;

/**
 * Implementación del servicio para la gestión de miembros del consejo.
 * 
 * Proporciona la lógica de negocio para las operaciones CRUD sobre miembros,
 * incluyendo búsquedas por consejo, persona y cargo. Registra auditoría para
 * todas las operaciones de creación, actualización y eliminación.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Service
@AllArgsConstructor
public class MiembroConsejoServiceImp implements MiembroConsejoService {

    /** Repositorio para acceso a datos de miembros del consejo */
    private MiembroConsejoRepository miembroConsejoRepository;
    
    /** Servicio de auditoría para registrar cambios */
    private AuditoriaServiceImp auditoriaServiceImp;
    
    /** Mapeador para convertir entre entidades y DTOs */
    private MiembroConsejoMapper mapper;

    /** Nombre de la entidad para auditoría */
    private static final String NAME_ENTITY = "MIEMBROS_CONSEJO";

    /**
     * Lista todos los miembros del consejo disponibles en el sistema.
     * 
     * @return lista completa de miembros convertidos a MiembroConsejoResponse
     */
    @Override
    public List<MiembroConsejoResponse> listar() {

        log.info("Inicio servicio: listar miembros de consejo");

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: listar miembros de consejo. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    /**
     * Obtiene un miembro del consejo específico por su identificador.
     * 
     * @param id identificador del miembro a obtener
     * @return el miembro encontrado, o null si no existe
     */
    @Override
    public MiembroConsejoResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener miembro de consejo por id");
        log.debug("Id miembro consejo: {}", id);

        MiembroConsejoResponse response = miembroConsejoRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Miembro Consejo no encontrado. Id: {}", id);
        } else {
            log.info("Miembro Consejo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener miembro de consejo por id");

        return response;
    }

    /**
     * Crea un nuevo miembro en el consejo en el sistema.
     * Registra la acción en auditoría.
     * 
     * @param miembroConsejoRequest datos del miembro a crear
     * @return el miembro creado con su identificador generado
     */
    @Override
    public MiembroConsejoResponse guardar(MiembroConsejoRequest miembroConsejoRequest) {

        log.info("Inicio servicio: guardar miembro de consejo");
        log.debug("Datos de entrada guardar miembro consejo: {}", miembroConsejoRequest);

        MiembroConsejoModel model = mapper.toEntity(miembroConsejoRequest);
        MiembroConsejoModel guardado = miembroConsejoRepository.save(model);

        log.info(
                "Miembro Consejo guardado correctamente. Id generado: {}",
                guardado.getIdMiembro()
        );

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdMiembro(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Registro de nuevo Miembro de consejo"
        );

        log.info("Fin servicio: guardar miembro de consejo");

        return mapper.toResponse(guardado);
    }

    /**
     * Elimina un miembro del consejo del sistema por su identificador.
     * Registra la acción en la auditoría.
     * 
     * @param id identificador del miembro a eliminar
     * @throws RuntimeException si el miembro no existe
     */
    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar miembro de consejo");
        log.debug("Id miembro consejo a eliminar: {}", id);

        MiembroConsejoModel model = miembroConsejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Miembro Consejo no encontrado para eliminar. Id: {}", id);
                    return new RuntimeException("Miembro Consejo no encontrada");
                });

        miembroConsejoRepository.deleteById(id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de Miembro Consejo"
        );

        log.info(
                "Fin servicio: eliminar miembro de consejo. Id eliminado: {}",
                id
        );
    }

    /**
     * Actualiza los datos de un miembro del consejo existente.
     * Registra la acción en auditoría con los datos antes y después.
     * 
     * @param id identificador del miembro a actualizar
     * @param miembroConsejoActualizado nuevos datos del miembro
     * @return el miembro actualizado
     * @throws RuntimeException si el miembro no existe
     */
    @Override
    public MiembroConsejoResponse actualizar(
            Integer id,
            MiembroConsejoRequest miembroConsejoActualizado) {

        log.info("Inicio servicio: actualizar miembro de consejo");
        log.debug("Id miembro consejo a actualizar: {}", id);
        log.debug(
                "Datos de entrada actualizar miembro consejo: {}",
                miembroConsejoActualizado
        );

        MiembroConsejoModel model = miembroConsejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Miembro Consejo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException(
                            "Miembro del Consejo no encontrado con id: " + id
                    );
                });

        String antes = model.toString();

        mapper.updateEntityFromRequest(miembroConsejoActualizado, model);

        MiembroConsejoModel actualizado = miembroConsejoRepository.save(model);

        log.info(
                "Miembro Consejo actualizado correctamente. Id: {}",
                actualizado.getIdMiembro()
        );

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdMiembro(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de Miembro Consejo"
        );

        log.info("Fin servicio: actualizar miembro de consejo");

        return mapper.toResponse(actualizado);
    }

    /**
     * Verifica si un miembro del consejo existe en el sistema.
     * 
     * @param id identificador del miembro a verificar
     * @return true si el miembro existe, false en caso contrario
     */
    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de miembro consejo. Id: {}", id);

        boolean existe = miembroConsejoRepository.existsById(id);

        log.debug(
                "Resultado existencia miembro consejo. Id: {}, Existe: {}",
                id,
                existe
        );

        return existe;
    }

    /**
     * Busca todos los miembros que pertenecen a un consejo específico.
     * 
     * @param idConsejo identificador del consejo
     * @return lista de miembros del consejo especificado
     */
    @Override
    public List<MiembroConsejoResponse> buscarPorConsejo(Integer idConsejo) {

        log.info("Inicio servicio: buscar miembros por consejo");
        log.debug("Id consejo: {}", idConsejo);

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findByConsejo_IdConsejo(idConsejo)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar miembros por consejo. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    /**
     * Busca todos los miembros asociados a una persona específica.
     * 
     * @param idPersona identificador de la persona
     * @return lista de miembros de esa persona
     */
    @Override
    public List<MiembroConsejoResponse> buscarPorPersona(Integer idPersona) {

        log.info("Inicio servicio: buscar miembros por persona");
        log.debug("Id persona: {}", idPersona);

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findByIdPersona(idPersona)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar miembros por persona. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    /**
     * Busca todos los miembros que ocupan un cargo específico.
     * 
     * @param idCargo identificador del cargo
     * @return lista de miembros que ocupan ese cargo
     */
    @Override
    public List<MiembroConsejoResponse> buscarPorCargo(Integer idCargo) {

        log.info("Inicio servicio: buscar miembros por cargo");
        log.debug("Id cargo: {}", idCargo);

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findByIdCargo(idCargo)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar miembros por cargo. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}