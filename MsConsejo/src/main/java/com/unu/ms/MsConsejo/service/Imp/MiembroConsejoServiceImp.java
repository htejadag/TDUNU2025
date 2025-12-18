package com.unu.ms.MsConsejo.service.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.MiembroConsejoMapper;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MiembroConsejoServiceImp implements MiembroConsejoService {

        
        private final MiembroConsejoRepository miembroConsejoRepository;
        private final MiembroConsejoMapper mapper;

        @Override
        public List<MiembroConsejoResponse> listar() {
                return miembroConsejoRepository.findAll().stream().map(mapper::toResponse).toList();
        }

        @Override
        public MiembroConsejoResponse obtenerPorId(Integer id) {
                return miembroConsejoRepository.findById(id).map(mapper::toResponse).orElse(null);
        }

        @Override
        public MiembroConsejoResponse guardar(MiembroConsejoRequest miembroConsejoRequest) {
                MiembroConsejoModel model = mapper.toEntity(miembroConsejoRequest);
                return mapper.toResponse(miembroConsejoRepository.save(model));
        }

        @Override
        public void eliminar(Integer id) {
                miembroConsejoRepository.deleteById(id);
        }

        @Override
        public MiembroConsejoResponse actualizar(Integer id, MiembroConsejoRequest miembroConsejoActualizado) {
                MiembroConsejoModel model = miembroConsejoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Miembro del Consejo no encontrado con id: " + id));
                mapper.updateEntityFromRequest(miembroConsejoActualizado, model);
                return mapper.toResponse(miembroConsejoRepository.save(model));
        }

        @Override
        public boolean existePorId(Integer id) {
                return miembroConsejoRepository.existsById(id);
        }

        @Override
        public List<MiembroConsejoResponse> buscarPorConsejo(Integer idConsejo) {
                return miembroConsejoRepository.findByConsejo_IdConsejo(idConsejo).stream().map(mapper::toResponse)
                                .toList();
        }

        @Override
        public List<MiembroConsejoResponse> buscarPorPersona(Integer idPersona) {
                return miembroConsejoRepository.findByIdPersona(idPersona).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<MiembroConsejoResponse> buscarPorCargo(Integer idCargo) {
                return miembroConsejoRepository.findByIdCargo(idCargo).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<MiembroConsejoResponse> listarActivosPorCargo(Integer idCargo) {
                return miembroConsejoRepository.findActivosByIdCargo(idCargo)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();
        }

}