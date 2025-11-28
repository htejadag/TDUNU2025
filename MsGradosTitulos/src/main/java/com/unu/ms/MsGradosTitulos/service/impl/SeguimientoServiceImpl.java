package com.unu.ms.MsGradosTitulos.service.impl;

import com.unu.ms.MsGradosTitulos.model.entity.Seguimiento;
import com.unu.ms.MsGradosTitulos.model.request.SeguimientoRequest;
import com.unu.ms.MsGradosTitulos.model.response.SeguimientoResponse;
import com.unu.ms.MsGradosTitulos.repository.SeguimientoRepository;
import com.unu.ms.MsGradosTitulos.service.SeguimientoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SeguimientoResponse> listar() {
        return seguimientoRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, SeguimientoResponse.class))
            .toList();
    }

    @Override
    public SeguimientoResponse obtenerPorId(Integer id) {
        return seguimientoRepository.findById(id)
            .map(entity -> modelMapper.map(entity, SeguimientoResponse.class))
            .orElse(null);
    }

    @Override
    public SeguimientoResponse guardar(SeguimientoRequest request) {
        Seguimiento entity = modelMapper.map(request, Seguimiento.class);
        Seguimiento saved = seguimientoRepository.save(entity);
        return modelMapper.map(saved, SeguimientoResponse.class);
    }

    @Override
    public SeguimientoResponse actualizar(SeguimientoRequest request) {
        if (request.getIdSeguimiento() == null) {
            throw new IllegalArgumentException("El ID del seguimiento es requerido para actualizar");
        }
        
        Seguimiento entity = modelMapper.map(request, Seguimiento.class);
        Seguimiento updated = seguimientoRepository.save(entity);
        return modelMapper.map(updated, SeguimientoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        seguimientoRepository.deleteById(id);
    }

    @Override
    public List<SeguimientoResponse> buscarPorEntidad(Integer idEntidadCatalogo, Integer entidadId) {
        return seguimientoRepository.findByIdEntidadCatalogoAndEntidadIdOrderByFechaRegistroDesc(
                idEntidadCatalogo, entidadId)
            .stream()
            .map(entity -> modelMapper.map(entity, SeguimientoResponse.class))
            .toList();
    }
}
