package com.unu.ms.MsConsejo.service.impl;

import com.unu.ms.MsConsejo.model.entity.Consejo;
import com.unu.ms.MsConsejo.model.entity.MiembroConsejo;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MiembroConsejoServiceImpl implements MiembroConsejoService {

    @Autowired
    private MiembroConsejoRepository miembroConsejoRepository;

    @Autowired
    private ConsejoRepository consejoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MiembroConsejoResponse> listar() {
        return miembroConsejoRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public MiembroConsejoResponse obtenerPorId(Integer id) {
        return miembroConsejoRepository.findById(id)
            .map(this::convertToResponse)
            .orElse(null);
    }

    @Override
    public MiembroConsejoResponse guardar(MiembroConsejoRequest request) {
        MiembroConsejo entity = convertToEntity(request);
        MiembroConsejo saved = miembroConsejoRepository.save(entity);
        return convertToResponse(saved);
    }

    @Override
    public MiembroConsejoResponse actualizar(MiembroConsejoRequest request) {
        if (request.getIdMiembro() == null) {
            throw new IllegalArgumentException("El ID del miembro es requerido para actualizar");
        }
        
        MiembroConsejo entity = convertToEntity(request);
        MiembroConsejo updated = miembroConsejoRepository.save(entity);
        return convertToResponse(updated);
    }

    @Override
    public void eliminar(Integer id) {
        miembroConsejoRepository.deleteById(id);
    }

    @Override
    public List<MiembroConsejoResponse> buscarPorConsejo(Integer idConsejo) {
        return miembroConsejoRepository.findByConsejo_IdConsejo(idConsejo)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public List<MiembroConsejoResponse> buscarPorPersona(Integer idPersona) {
        return miembroConsejoRepository.findByIdPersona(idPersona)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public List<MiembroConsejoResponse> buscarPorCargo(Integer idCargo) {
        return miembroConsejoRepository.findByIdCargo(idCargo)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    private MiembroConsejo convertToEntity(MiembroConsejoRequest request) {
        MiembroConsejo entity = modelMapper.map(request, MiembroConsejo.class);
        
        if (request.getIdConsejo() != null) {
            Consejo consejo = consejoRepository.findById(request.getIdConsejo())
                .orElseThrow(() -> new IllegalArgumentException("Consejo no encontrado"));
            entity.setConsejo(consejo);
        }
        
        return entity;
    }

    private MiembroConsejoResponse convertToResponse(MiembroConsejo entity) {
        MiembroConsejoResponse response = modelMapper.map(entity, MiembroConsejoResponse.class);
        if (entity.getConsejo() != null) {
            response.setIdConsejo(entity.getConsejo().getIdConsejo());
        }
        return response;
    }
}
