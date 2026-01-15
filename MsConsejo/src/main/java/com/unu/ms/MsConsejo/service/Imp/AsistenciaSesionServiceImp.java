package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.AsistenciaSesionMapper;
import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;
import com.unu.ms.MsConsejo.model.response.AsistenciaResumenResponse;
import com.unu.ms.MsConsejo.repository.AsistenciaSesionRepository;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.AsistenciaSesionService;

@Slf4j
@Service
@AllArgsConstructor
public class AsistenciaSesionServiceImp implements AsistenciaSesionService {

        private final AsistenciaSesionRepository asistenciaSesionRepository;
        private final SesionConsejoRepository sesionConsejoRepository;
        private final AsistenciaSesionMapper mapper;
        
        // IDs de estados de asistencia (basados en catalogo)
        private static final Integer ESTADO_PRESENTE = 1;
        private static final Integer ESTADO_AUSENTE = 2;
        private static final Integer ESTADO_JUSTIFICADO = 3;

        @Override
        public List<AsistenciaSesionResponse> listar() {
                return asistenciaSesionRepository.findAll().stream().map(mapper::toResponse).toList();
        }

        @Override
        public AsistenciaSesionResponse obtenerPorId(Integer id) {
                return asistenciaSesionRepository.findById(id).map(mapper::toResponse).orElse(null);
        }

        @Override
        public AsistenciaSesionResponse guardar(AsistenciaSesionRequest asistenciaSesionRequest) {
                AsistenciaSesionModel model = mapper.toEntity(asistenciaSesionRequest);
                return mapper.toResponse(asistenciaSesionRepository.save(model));
        }

        @Override
        public void eliminar(Integer id) {
                asistenciaSesionRepository.deleteById(id);
        }

        @Override
        public AsistenciaSesionResponse actualizar(Integer id, AsistenciaSesionRequest asistenciaSesionActualizado) {
                AsistenciaSesionModel model = asistenciaSesionRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Asistencia no encontrado con id: " + id));
                mapper.updateEntityFromRequest(asistenciaSesionActualizado, model);
                return mapper.toResponse(asistenciaSesionRepository.save(model));
        }

        @Override
        public boolean existePorId(Integer id) {
                return asistenciaSesionRepository.existsById(id);
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorSesion(Integer idSesion) {
                return asistenciaSesionRepository.findBySesion_IdSesion(idSesion).stream().map(mapper::toResponse)
                                .toList();
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorMiembro(Integer idMiembro) {
                return asistenciaSesionRepository.findByMiembro_IdMiembro(idMiembro).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorEstadoAsistencia(Integer idEstadoAsistencia) {
                return asistenciaSesionRepository.findByIdEstadoAsistencia(idEstadoAsistencia).stream()
                                .map(mapper::toResponse).toList();
        }

        @Override
        public AsistenciaResumenResponse obtenerResumenPorSesion(Integer idSesion) {
                SesionConsejoModel sesion = sesionConsejoRepository.findById(idSesion)
                                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con id: " + idSesion));
                
                long totalMiembros = asistenciaSesionRepository.countBySesion_IdSesion(idSesion);
                long presentes = asistenciaSesionRepository.countBySesion_IdSesionAndIdEstadoAsistencia(idSesion, ESTADO_PRESENTE);
                long ausentes = asistenciaSesionRepository.countBySesion_IdSesionAndIdEstadoAsistencia(idSesion, ESTADO_AUSENTE);
                long justificados = asistenciaSesionRepository.countBySesion_IdSesionAndIdEstadoAsistencia(idSesion, ESTADO_JUSTIFICADO);
                
                double porcentajeAsistencia = totalMiembros > 0 
                                ? (double) presentes / totalMiembros * 100 
                                : 0.0;

                return AsistenciaResumenResponse.builder()
                                .idSesion(idSesion)
                                .numeroSesion(sesion.getNumeroSesion())
                                .totalMiembros((int) totalMiembros)
                                .presentes((int) presentes)
                                .ausentes((int) ausentes)
                                .justificados((int) justificados)
                                .porcentajeAsistencia(Math.round(porcentajeAsistencia * 100.0) / 100.0)
                                .build();
        }

}