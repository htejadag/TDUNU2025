package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.message.KafkaProducerPublisher;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.SesionConsejoMapper;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.SesionConsejoService;

/**
 * Implementación del servicio para la gestión de sesiones de consejo.
 * 
 * Proporciona la lógica de negocio para las operaciones CRUD sobre sesiones,
 * incluyendo búsquedas por consejo, número, fecha y tipo de sesión. Integra
 * con Kafka para enviar eventos y registra auditoría para todas las operaciones.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Service
@AllArgsConstructor
public class SesionConsejoServiceImp implements SesionConsejoService {

        /** Repositorio para acceso a datos de sesiones de consejo */
        private SesionConsejoRepository sesionConsejoRepository;
        
        /** Servicio de auditoría para registrar cambios */
        private AuditoriaServiceImp auditoriaServiceImp;
        
        /** Servicio para enviar eventos a Kafka */
        private KafkaProducerPublisher kafkaProducerService;
        
        /** Mapeador para convertir entre entidades y DTOs */
        private SesionConsejoMapper mapper;

        /** Nombre de la entidad para auditoría */
        private static final String NAME_ENTITY = "SESION_CONSEJO";

        /**
         * Lista todas las sesiones de consejo disponibles en el sistema.
         * 
         * @return lista completa de sesiones convertidas a SesionConsejoResponse
         */
        @Override
        public List<SesionConsejoResponse> listar() {

                log.info("Inicio servicio: listar sesiones de consejo");

                List<SesionConsejoResponse> resultado = sesionConsejoRepository.findAll()
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: listar sesiones de consejo. Total registros: {}",
                                resultado.size());

                return resultado;
        }

        /**
         * Obtiene una sesión de consejo específica por su identificador.
         * 
         * @param id identificador de la sesión a obtener
         * @return la sesión encontrada, o null si no existe
         */
        @Override
        public SesionConsejoResponse obtenerPorId(Integer id) {

                log.info("Inicio servicio: obtener sesión de consejo por id");
                log.debug("Id sesión consejo: {}", id);

                SesionConsejoResponse response = sesionConsejoRepository.findById(id)
                                .map(mapper::toResponse)
                                .orElse(null);

                if (response == null) {
                        log.warn("Sesión Consejo no encontrada. Id: {}", id);
                } else {
                        log.info("Sesión Consejo encontrada. Id: {}", id);
                }

                log.info("Fin servicio: obtener sesión de consejo por id");

                return response;
        }

        /**
         * Crea una nueva sesión de consejo en el sistema.
         * Envía un evento a Kafka y registra la acción en auditoría.
         * 
         * @param sesionConsejoRequest datos de la sesión a crear
         * @return la sesión creada con su identificador generado
         */
        @Override
        public SesionConsejoResponse guardar(SesionConsejoRequest sesionConsejoRequest) {

                log.info("Inicio servicio: guardar sesión de consejo");
                log.debug("Datos de entrada guardar sesión consejo: {}", sesionConsejoRequest);

                SesionConsejoModel model = mapper.toEntity(sesionConsejoRequest);
                SesionConsejoModel guardado = sesionConsejoRepository.save(model);

                // Enviar evento a Kafka
                kafkaProducerService.sendSesionConsejoModel(sesionConsejoRequest);

                log.info(
                                "Sesión Consejo guardada correctamente. Id generado: {}",
                                guardado.getIdSesion());

                auditoriaServiceImp.registrarAccion(
                                NAME_ENTITY,
                                guardado.getIdSesion(),
                                "CREATE",
                                10,
                                null,
                                guardado.toString(),
                                "Registro de nuevo Sesion de Consejo");

                log.info("Fin servicio: guardar sesión de consejo");

                return mapper.toResponse(guardado);
        }

        /**
         * Elimina una sesión de consejo del sistema por su identificador.
         * Registra la acción en la auditoría.
         * 
         * @param id identificador de la sesión a eliminar
         * @throws RuntimeException si la sesión no existe
         */
        @Override
        public void eliminar(Integer id) {

                log.info("Inicio servicio: eliminar sesión de consejo");
                log.debug("Id sesión consejo a eliminar: {}", id);

                SesionConsejoModel model = sesionConsejoRepository.findById(id)
                                .orElseThrow(() -> {
                                        log.warn("Sesión Consejo no encontrada para eliminar. Id: {}", id);
                                        return new RuntimeException("Sesion Consejo no encontrada");
                                });

                sesionConsejoRepository.deleteById(id);

                auditoriaServiceImp.registrarAccion(
                                NAME_ENTITY,
                                id,
                                "DELETE",
                                10,
                                model.toString(),
                                null,
                                "Eliminación de Sesion Consejo");

                log.info(
                                "Fin servicio: eliminar sesión de consejo. Id eliminado: {}",
                                id);
        }

        /**
         * Actualiza los datos de una sesión de consejo existente.
         * Registra la acción en auditoría con los datos antes y después.
         * 
         * @param id identificador de la sesión a actualizar
         * @param sesionConsejoActualizado nuevos datos de la sesión
         * @return la sesión actualizada
         * @throws RuntimeException si la sesión no existe
         */
        @Override
        public SesionConsejoResponse actualizar(
                        Integer id,
                        SesionConsejoRequest sesionConsejoActualizado) {

                log.info("Inicio servicio: actualizar sesión de consejo");
                log.debug("Id sesión consejo a actualizar: {}", id);
                log.debug(
                                "Datos de entrada actualizar sesión consejo: {}",
                                sesionConsejoActualizado);

                SesionConsejoModel model = sesionConsejoRepository.findById(id)
                                .orElseThrow(() -> {
                                        log.warn("Sesión Consejo no encontrada para actualizar. Id: {}", id);
                                        return new RuntimeException(
                                                        "Sesion del Consejo no encontrado con id: " + id);
                                });

                String antes = model.toString();

                mapper.updateEntityFromRequest(sesionConsejoActualizado, model);

                SesionConsejoModel actualizado = sesionConsejoRepository.save(model);

                log.info(
                                "Sesión Consejo actualizada correctamente. Id: {}",
                                actualizado.getIdSesion());

                auditoriaServiceImp.registrarAccion(
                                NAME_ENTITY,
                                actualizado.getIdSesion(),
                                "UPDATE",
                                10,
                                antes,
                                actualizado.toString(),
                                "Actualización de Sesion Consejo");

                log.info("Fin servicio: actualizar sesión de consejo");

                return mapper.toResponse(actualizado);
        }

        /**
         * Verifica si una sesión de consejo existe en el sistema.
         * 
         * @param id identificador de la sesión a verificar
         * @return true si la sesión existe, false en caso contrario
         */
        @Override
        public boolean existePorId(Integer id) {

                log.debug("Validando existencia de sesión consejo. Id: {}", id);

                boolean existe = sesionConsejoRepository.existsById(id);

                log.debug(
                                "Resultado existencia sesión consejo. Id: {}, Existe: {}",
                                id,
                                existe);

                return existe;
        }

        /**
         * Busca todas las sesiones que pertenecen a un consejo específico.
         * 
         * @param idConsejo identificador del consejo
         * @return lista de sesiones del consejo especificado
         */
        @Override
        public List<SesionConsejoResponse> buscarPorConsejo(Integer idConsejo) {

                log.info("Inicio servicio: buscar sesiones por consejo");
                log.debug("Id consejo: {}", idConsejo);

                List<SesionConsejoResponse> resultado = sesionConsejoRepository.findByConsejo_IdConsejo(idConsejo)
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: buscar sesiones por consejo. Total registros: {}",
                                resultado.size());

                return resultado;
        }

        /**
         * Busca una sesión de consejo específica por su número de sesión.
         * 
         * @param numeroSesion número identificador único de la sesión
         * @return la sesión encontrada, o null si no existe
         */
        @Override
        public SesionConsejoResponse buscarPorNumeroSesion(String numeroSesion) {

                log.info("Inicio servicio: buscar sesión por número");
                log.debug("Número sesión: {}", numeroSesion);

                SesionConsejoResponse response = sesionConsejoRepository.findByNumeroSesion(numeroSesion)
                                .map(mapper::toResponse)
                                .orElse(null);

                if (response == null) {
                        log.warn("Sesión Consejo no encontrada. Número: {}", numeroSesion);
                } else {
                        log.info("Sesión Consejo encontrada. Número: {}", numeroSesion);
                }

                log.info("Fin servicio: buscar sesión por número");

                return response;
        }

        /**
         * Busca todas las sesiones que se realizaron en una fecha específica.
         * 
         * @param fechaSesion fecha de las sesiones a buscar
         * @return lista de sesiones de la fecha especificada
         */
        @Override
        public List<SesionConsejoResponse> buscarPorFecha(LocalDate fechaSesion) {

                log.info("Inicio servicio: buscar sesiones por fecha");
                log.debug("Fecha sesión: {}", fechaSesion);

                List<SesionConsejoResponse> resultado = sesionConsejoRepository.findByFechaSesion(fechaSesion)
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: buscar sesiones por fecha. Total registros: {}",
                                resultado.size());

                return resultado;
        }

        /**
         * Busca todas las sesiones de un tipo específico.
         * 
         * @param idTipoSesion identificador del tipo de sesión
         * @return lista de sesiones del tipo especificado
         */
        @Override
        public List<SesionConsejoResponse> buscarPorTipoSesion(Integer idTipoSesion) {

                log.info("Inicio servicio: buscar sesiones por tipo");
                log.debug("Id tipo sesión: {}", idTipoSesion);

                List<SesionConsejoResponse> resultado = sesionConsejoRepository.findByIdTipoSesion(idTipoSesion)
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: buscar sesiones por tipo. Total registros: {}",
                                resultado.size());

                return resultado;
        }

}