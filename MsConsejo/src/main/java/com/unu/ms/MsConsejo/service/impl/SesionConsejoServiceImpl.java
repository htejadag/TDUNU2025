package com.unu.ms.MsConsejo.service.impl;

import com.unu.ms.MsConsejo.model.entity.Consejo;
import com.unu.ms.MsConsejo.model.entity.SesionConsejo;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.SesionConsejoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SesionConsejoServiceImpl implements SesionConsejoService {

    @Autowired
    private SesionConsejoRepository sesionConsejoRepository;

    @Autowired
    private ConsejoRepository consejoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SesionConsejoResponse> listar() {
        return sesionConsejoRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public SesionConsejoResponse obtenerPorId(Integer id) {
        return sesionConsejoRepository.findById(id)
            .map(this::convertToResponse)
            .orElse(null);
    }

    @Override
    public SesionConsejoResponse guardar(SesionConsejoRequest request) {
        SesionConsejo entity = convertToEntity(request);
        SesionConsejo saved = sesionConsejoRepository.save(entity);
        return convertToResponse(saved);
    }

    @Override
    public SesionConsejoResponse actualizar(SesionConsejoRequest request) {
        if (request.getIdSesion() == null) {
            throw new IllegalArgumentException("El ID de la sesión es requerido para actualizar");
        }
        
        SesionConsejo entity = convertToEntity(request);
        SesionConsejo updated = sesionConsejoRepository.save(entity);
        return convertToResponse(updated);
    }

    @Override
    public void eliminar(Integer id) {
        sesionConsejoRepository.deleteById(id);
    }

    @Override
    public List<SesionConsejoResponse> buscarPorConsejo(Integer idConsejo) {
        return sesionConsejoRepository.findByConsejo_IdConsejo(idConsejo)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public SesionConsejoResponse buscarPorNumeroSesion(String numeroSesion) {
        return sesionConsejoRepository.findByNumeroSesion(numeroSesion)
            .map(this::convertToResponse)
            .orElse(null);
    }

    @Override
    public List<SesionConsejoResponse> buscarPorFecha(LocalDate fechaSesion) {
        return sesionConsejoRepository.findByFechaSesion(fechaSesion)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public List<SesionConsejoResponse> buscarPorTipoSesion(Integer idTipoSesion) {
        return sesionConsejoRepository.findByIdTipoSesion(idTipoSesion)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    private SesionConsejo convertToEntity(SesionConsejoRequest request) {
        SesionConsejo entity = modelMapper.map(request, SesionConsejo.class);
        
        if (request.getIdConsejo() != null) {
            Consejo consejo = consejoRepository.findById(request.getIdConsejo())
                .orElseThrow(() -> new IllegalArgumentException("Consejo no encontrado"));
            entity.setConsejo(consejo);
        }
        
        return entity;
    }

    private SesionConsejoResponse convertToResponse(SesionConsejo entity) {
        SesionConsejoResponse response = modelMapper.map(entity, SesionConsejoResponse.class);
        if (entity.getConsejo() != null) {
            response.setIdConsejo(entity.getConsejo().getIdConsejo());
        }
        return response;
    }
}
