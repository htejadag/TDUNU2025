package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.SesionConsejoMapper;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.SesionConsejoService;

@Slf4j
@Service
public class SesionConsejoServiceImp implements SesionConsejoService {

        SesionConsejoRepository sesionConsejoRepository;
        SesionConsejoMapper mapper;

        @Override
        public List<SesionConsejoResponse> listar() {
                return sesionConsejoRepository.findAll().stream().map(mapper::toResponse).toList();
        }

        @Override
        public SesionConsejoResponse obtenerPorId(Integer id) {
                return sesionConsejoRepository.findById(id).map(mapper::toResponse).orElse(null);
        }

        @Override
        public SesionConsejoResponse guardar(SesionConsejoRequest sesionConsejoRequest) {
                SesionConsejoModel model = mapper.toEntity(sesionConsejoRequest);
                return mapper.toResponse(sesionConsejoRepository.save(model));
        }

        @Override
        public void eliminar(Integer id) {
                sesionConsejoRepository.deleteById(id);
        }

        @Override
        public SesionConsejoResponse actualizar(Integer id, SesionConsejoRequest sesionConsejoActualizado) {
                SesionConsejoModel model = sesionConsejoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Sesion del Consejo no encontrado con id: " + id));
                mapper.updateEntityFromRequest(sesionConsejoActualizado, model);
                return mapper.toResponse(sesionConsejoRepository.save(model));
        }

        @Override
        public boolean existePorId(Integer id) {
                return sesionConsejoRepository.existsById(id);
        }

        @Override
        public List<SesionConsejoResponse> buscarPorConsejo(Integer idConsejo) {
                return sesionConsejoRepository.findByConsejo_IdConsejo(idConsejo).stream().map(mapper::toResponse).toList();
        }

        @Override
        public SesionConsejoResponse buscarPorNumeroSesion(String numeroSesion) {
                return sesionConsejoRepository.findByNumeroSesion(numeroSesion).map(mapper::toResponse).orElse(null);
        }

        @Override
        public List<SesionConsejoResponse> buscarPorFecha(LocalDate fechaSesion) {
                return sesionConsejoRepository.findByFechaSesion(fechaSesion).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<SesionConsejoResponse> buscarPorTipoSesion(Integer idTipoSesion) {
                return sesionConsejoRepository.findByIdTipoSesion(idTipoSesion).stream().map(mapper::toResponse).toList();
        }

}