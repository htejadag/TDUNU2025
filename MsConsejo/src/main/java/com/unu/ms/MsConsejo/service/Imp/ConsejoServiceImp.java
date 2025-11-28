package com.unu.ms.MsConsejo.service.Imp;


import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.ConsejoMapper;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsejoServiceImp implements ConsejoService {
    
    @Autowired
    private ConsejoRepository consejoRepository;
    
    @Autowired
    private  ConsejoMapper mapper;

    @Override
    public Iterable<ConsejoModel> listar() {
        return consejoRepository.findAll();
    }

    @Override
    public ConsejoResponse obtenerPorId(Integer id) {
        ConsejoModel consejoModel = consejoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado con id: " + id));
        return mapper.toResponse(consejoModel);
    }

    @Override
    public ConsejoResponse crear(ConsejoRequest consejoRequest) {
        ConsejoModel consejoModel = mapper.toEntity(consejoRequest);
        return mapper.toResponse(consejoRepository.save(consejoModel));
    }

    @Override
    public void eliminar(Integer id) {
        consejoRepository.deleteById(id);
    }

    @Override
    public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado) {
        ConsejoModel consejoExistente = consejoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consejo no encontrado con id: " + id));
        mapper.updateEntityFromRequest(consejoActualizado, consejoExistente);

        return mapper.toResponse(consejoRepository.save(consejoExistente));
    }

    @Override
    public boolean existePorId(Integer id) {
        return consejoRepository.existsById(id);
    }

       

}
