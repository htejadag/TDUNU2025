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

@Slf4j
@Service
@AllArgsConstructor
public class SesionConsejoServiceImp implements SesionConsejoService {

        SesionConsejoRepository sesionConsejoRepository;
        AuditoriaServiceImp auditoriaServiceImp;
        KafkaProducerPublisher kafkaProducerService;
        SesionConsejoMapper mapper;

        private static final String NAME_ENTITY = "SESION_CONSEJO";

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

        @Override
        public SesionConsejoResponse guardar(SesionConsejoRequest sesionConsejoRequest) {

                log.info("Inicio servicio: guardar sesión de consejo");
                log.debug("Datos de entrada guardar sesión consejo: {}", sesionConsejoRequest);

                SesionConsejoModel model = mapper.toEntity(sesionConsejoRequest);
                SesionConsejoModel guardado = sesionConsejoRepository.save(model);

                // Kafka send Message
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