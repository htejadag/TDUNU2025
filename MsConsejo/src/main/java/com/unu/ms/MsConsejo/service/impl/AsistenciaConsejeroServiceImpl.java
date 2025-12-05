package com.unu.ms.MsConsejo.service.impl;

import com.unu.ms.MsConsejo.model.entity.AsistenciaConsejero;
import com.unu.ms.MsConsejo.model.entity.SesionConsejo;
import com.unu.ms.MsConsejo.model.request.AsistenciaConsejeroRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaConsejeroResponse;
import com.unu.ms.MsConsejo.repository.AsistenciaConsejeroRepository;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.AsistenciaConsejeroService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AsistenciaConsejeroServiceImpl implements AsistenciaConsejeroService {

    @Autowired
    private AsistenciaConsejeroRepository asistenciaConsejeroRepository;

    @Autowired
    private SesionConsejoRepository sesionConsejoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AsistenciaConsejeroResponse> listar() {
        return asistenciaConsejeroRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public AsistenciaConsejeroResponse obtenerPorId(Integer id) {
        return asistenciaConsejeroRepository.findById(id)
            .map(this::convertToResponse)
            .orElse(null);
    }

    @Override
    public AsistenciaConsejeroResponse guardar(AsistenciaConsejeroRequest request) {
        AsistenciaConsejero entity = convertToEntity(request);
        AsistenciaConsejero saved = asistenciaConsejeroRepository.save(entity);
        return convertToResponse(saved);
    }

    @Override
    public AsistenciaConsejeroResponse actualizar(AsistenciaConsejeroRequest request) {
        if (request.getIdAsistencia() == null) {
            throw new IllegalArgumentException("El ID de la asistencia es requerido para actualizar");
        }
        
        AsistenciaConsejero entity = convertToEntity(request);
        AsistenciaConsejero updated = asistenciaConsejeroRepository.save(entity);
        return convertToResponse(updated);
    }

    @Override
    public void eliminar(Integer id) {
        asistenciaConsejeroRepository.deleteById(id);
    }

    @Override
    public List<AsistenciaConsejeroResponse> buscarPorSesion(Integer idSesion) {
        return asistenciaConsejeroRepository.findBySesionConsejo_IdSesion(idSesion)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public List<AsistenciaConsejeroResponse> buscarPorMiembro(Integer idMiembro) {
        return asistenciaConsejeroRepository.findByIdMiembro(idMiembro)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    @Override
    public List<AsistenciaConsejeroResponse> buscarPorEstadoAsistencia(Integer idEstadoAsistencia) {
        return asistenciaConsejeroRepository.findByIdEstadoAsistencia(idEstadoAsistencia)
            .stream()
            .map(this::convertToResponse)
            .toList();
    }

    private AsistenciaConsejero convertToEntity(AsistenciaConsejeroRequest request) {
        AsistenciaConsejero entity = modelMapper.map(request, AsistenciaConsejero.class);
        
        if (request.getIdSesion() != null) {
            SesionConsejo sesion = sesionConsejoRepository.findById(request.getIdSesion())
                .orElseThrow(() -> new IllegalArgumentException("Sesión no encontrada"));
            entity.setSesionConsejo(sesion);
        }
        
        return entity;
    }

    private AsistenciaConsejeroResponse convertToResponse(AsistenciaConsejero entity) {
        AsistenciaConsejeroResponse response = modelMapper.map(entity, AsistenciaConsejeroResponse.class);
        if (entity.getSesionConsejo() != null) {
            response.setIdSesion(entity.getSesionConsejo().getIdSesion());
        }
        return response;
    }
}
