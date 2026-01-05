package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.mapper.AsistenciaSesionMapper;
import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;
import com.unu.ms.MsConsejo.repository.AsistenciaSesionRepository;
import com.unu.ms.MsConsejo.service.AsistenciaSesionService;

@Slf4j
@Service
@AllArgsConstructor
public class AsistenciaSesionServiceImp implements AsistenciaSesionService {

        AsistenciaSesionRepository asistenciaSesionRepository;
        AuditoriaServiceImp auditoriaServiceImp;
        AsistenciaSesionMapper mapper;

        private static final String NAME_ENTITY = "ASISTENCIA_SESION";

        @Override
        public List<AsistenciaSesionResponse> listar() {

                log.info("Inicio servicio: listar asistencias de sesión");

                List<AsistenciaSesionResponse> resultado = asistenciaSesionRepository.findAll()
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: listar asistencias de sesión. Total registros: {}",
                                resultado.size());

                return resultado;
        }

        @Override
        public AsistenciaSesionResponse obtenerPorId(Integer id) {

                log.info("Inicio servicio: obtener asistencia de sesión por id");
                log.debug("Id asistencia sesión: {}", id);

                AsistenciaSesionResponse response = asistenciaSesionRepository.findById(id)
                                .map(mapper::toResponse)
                                .orElse(null);

                if (response == null) {
                        log.warn("Asistencia de sesión no encontrada. Id: {}", id);
                } else {
                        log.info("Asistencia de sesión encontrada. Id: {}", id);
                }

                log.info("Fin servicio: obtener asistencia de sesión por id");

                return response;
        }

        @Override
        public AsistenciaSesionResponse guardar(AsistenciaSesionRequest asistenciaSesionRequest) {

                log.info("Inicio servicio: guardar asistencia de sesión");
                log.debug("Datos de entrada guardar asistencia sesión: {}", asistenciaSesionRequest);

                AsistenciaSesionModel model = mapper.toEntity(asistenciaSesionRequest);

                AsistenciaSesionModel guardado = asistenciaSesionRepository.save(model);

                log.info(
                                "Asistencia de sesión guardada correctamente. Id generado: {}",
                                guardado.getIdAsistencia());

                auditoriaServiceImp.registrarAccion(
                                NAME_ENTITY,
                                guardado.getIdAsistencia(),
                                "CREATE",
                                10,
                                null,
                                guardado.toString(),
                                "Registro de asistencia de sesión");

                log.info("Fin servicio: guardar asistencia de sesión");

                return mapper.toResponse(guardado);
        }

        @Override
        public void eliminar(Integer id) {

                log.info("Inicio servicio: eliminar asistencia de sesión");
                log.debug("Id asistencia sesión a eliminar: {}", id);

                AsistenciaSesionModel model = asistenciaSesionRepository.findById(id)
                                .orElseThrow(() -> {
                                        log.warn("Asistencia de sesión no encontrada para eliminar. Id: {}", id);
                                        return new RuntimeException("Asistencia no encontrada con id: " + id);
                                });

                asistenciaSesionRepository.deleteById(id);

                auditoriaServiceImp.registrarAccion(
                                NAME_ENTITY,
                                id,
                                "DELETE",
                                10,
                                model.toString(),
                                null,
                                "Eliminación de asistencia de sesión");

                log.info("Fin servicio: eliminar asistencia de sesión. Id eliminado: {}", id);
        }

        @Override
        public AsistenciaSesionResponse actualizar(
                        Integer id,
                        AsistenciaSesionRequest asistenciaSesionActualizado) {

                log.info("Inicio servicio: actualizar asistencia de sesión");
                log.debug("Id asistencia sesión a actualizar: {}", id);
                log.debug("Datos de entrada actualizar asistencia sesión: {}", asistenciaSesionActualizado);

                AsistenciaSesionModel model = asistenciaSesionRepository.findById(id)
                                .orElseThrow(() -> {
                                        log.warn("Asistencia de sesión no encontrada para actualizar. Id: {}", id);
                                        return new RuntimeException("Asistencia no encontrada con id: " + id);
                                });

                String antes = model.toString();

                mapper.updateEntityFromRequest(asistenciaSesionActualizado, model);

                AsistenciaSesionModel actualizado = asistenciaSesionRepository.save(model);

                auditoriaServiceImp.registrarAccion(
                                NAME_ENTITY,
                                actualizado.getIdAsistencia(),
                                "UPDATE",
                                10,
                                antes,
                                actualizado.toString(),
                                "Actualización de asistencia de sesión");

                log.info(
                                "Asistencia de sesión actualizada correctamente. Id: {}",
                                actualizado.getIdAsistencia());

                log.info("Fin servicio: actualizar asistencia de sesión");

                return mapper.toResponse(actualizado);
        }

        @Override
        public boolean existePorId(Integer id) {

                log.debug("Validando existencia de asistencia de sesión. Id: {}", id);

                boolean existe = asistenciaSesionRepository.existsById(id);

                log.debug(
                                "Resultado existencia asistencia sesión. Id: {}, Existe: {}",
                                id,
                                existe);

                return existe;
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorSesion(Integer idSesion) {

                log.info("Inicio servicio: buscar asistencias por sesión");
                log.debug("Id sesión: {}", idSesion);

                List<AsistenciaSesionResponse> resultado = asistenciaSesionRepository.findBySesion_IdSesion(idSesion)
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: buscar asistencias por sesión. Total registros: {}",
                                resultado.size());

                return resultado;
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorMiembro(Integer idMiembro) {

                log.info("Inicio servicio: buscar asistencias por miembro");
                log.debug("Id miembro: {}", idMiembro);

                List<AsistenciaSesionResponse> resultado = asistenciaSesionRepository.findByMiembro(idMiembro)
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: buscar asistencias por miembro. Total registros: {}",
                                resultado.size());

                return resultado;
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorEstadoAsistencia(Integer idEstadoAsistencia) {

                log.info("Inicio servicio: buscar asistencias por estado de asistencia");
                log.debug("Id estado asistencia: {}", idEstadoAsistencia);

                List<AsistenciaSesionResponse> resultado = asistenciaSesionRepository
                                .findByIdEstadoAsistencia(idEstadoAsistencia)
                                .stream()
                                .map(mapper::toResponse)
                                .toList();

                log.info(
                                "Fin servicio: buscar asistencias por estado de asistencia. Total registros: {}",
                                resultado.size());

                return resultado;
        }
}