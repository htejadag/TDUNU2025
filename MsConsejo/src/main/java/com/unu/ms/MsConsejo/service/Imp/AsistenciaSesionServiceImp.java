package com.unu.ms.MsConsejo.service.Imp;

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
public class AsistenciaSesionServiceImp implements AsistenciaSesionService {

        AsistenciaSesionRepository asistenciaSesionRepository;
        AsistenciaSesionMapper mapper;

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
                return asistenciaSesionRepository.findByMiembro(idMiembro).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<AsistenciaSesionResponse> buscarPorEstadoAsistencia(Integer idEstadoAsistencia) {
                return asistenciaSesionRepository.findByIdEstadoAsistencia(idEstadoAsistencia).stream()
                                .map(mapper::toResponse).toList();
        }

}