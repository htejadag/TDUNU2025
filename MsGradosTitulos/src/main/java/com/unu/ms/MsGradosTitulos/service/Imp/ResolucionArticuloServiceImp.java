package com.unu.ms.MsGradosTitulos.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticuloModel;
import com.unu.ms.MsGradosTitulos.model.mapper.ResolucionArticuloMapper;
import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsGradosTitulos.repository.ResolucionArticuloRepository;
import com.unu.ms.MsGradosTitulos.service.ResolucionArticuloService;

@Slf4j
@Service
public class ResolucionArticuloServiceImp implements ResolucionArticuloService {

    @Autowired
    ResolucionArticuloRepository resolucionArticuloRepository;

    @Autowired
    ResolucionArticuloMapper mapper;

    @Override
    public List<ResolucionArticuloResponse> listar() {
        return resolucionArticuloRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ResolucionArticuloResponse obtenerPorId(Integer id) {
        return resolucionArticuloRepository.findById(id)
                .map(mapper::toResponse).orElse(null);
    }

    @Override
    public ResolucionArticuloResponse guardar(ResolucionArticuloRequest resolucionArticuloRequest) {
        ResolucionArticuloModel model = mapper.toEntity(resolucionArticuloRequest);
        return mapper.toResponse(resolucionArticuloRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        resolucionArticuloRepository.deleteById(id);
    }

    @Override
    public ResolucionArticuloResponse actualizar(Integer id, ResolucionArticuloRequest resolucionArticuloActualizado) {
        ResolucionArticuloModel model = resolucionArticuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resolucion Articulo no encontrado con id: " + id));
        mapper.updateEntityFromRequest(resolucionArticuloActualizado, model);
        return mapper.toResponse(resolucionArticuloRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return resolucionArticuloRepository.existsById(id);
    }

    @Override
    public List<ResolucionArticuloResponse> buscarPorResolucion(Integer idResolucion) {
        return resolucionArticuloRepository.findByResolucionOrderByNumeroArticuloAsc(idResolucion).stream().map(mapper:: toResponse).toList();
    }

}
