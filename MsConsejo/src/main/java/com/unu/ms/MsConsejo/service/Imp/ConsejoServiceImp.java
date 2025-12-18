package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.ConsejoMapper;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;

@Slf4j
@Service
public class ConsejoServiceImp implements ConsejoService {

    private ConsejoRepository consejoRepository;
    private ConsejoMapper mapper;

    @Override
    public List<ConsejoResponse> listar() {
            return consejoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ConsejoResponse obtenerPorId(Integer id) {
            return consejoRepository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    public ConsejoResponse guardar(ConsejoRequest consejoRequest) {
            ConsejoModel model = mapper.toEntity(consejoRequest);
            return mapper.toResponse(consejoRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
            consejoRepository.deleteById(id);
    }

    @Override
    public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado) {
            ConsejoModel model = consejoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Consejo no encontrado con id: " + id));
            mapper.updateEntityFromRequest(consejoActualizado, model);
            return mapper.toResponse(consejoRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
            return consejoRepository.existsById(id);
    }

    @Override
    public ConsejoResponse buscarPorNombre(String nombre) {
            return consejoRepository.findByNombre(nombre).map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<ConsejoResponse> buscarPorEstado(Integer idEstado) {
            return consejoRepository.findByIdEstado(idEstado).stream().map(mapper::toResponse).toList();
    }

}
