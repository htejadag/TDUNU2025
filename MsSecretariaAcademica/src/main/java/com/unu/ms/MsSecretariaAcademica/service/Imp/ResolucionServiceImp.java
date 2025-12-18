package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;
import com.unu.ms.MsSecretariaAcademica.model.mapper.ResolucionMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionResponse;
import com.unu.ms.MsSecretariaAcademica.repository.ExpedienteRepository;
import com.unu.ms.MsSecretariaAcademica.repository.ResolucionRepository;
import com.unu.ms.MsSecretariaAcademica.repository.SolicitudRepository;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionService;

@Slf4j
@Service
public class ResolucionServiceImp implements ResolucionService {

    ResolucionRepository resolucionRepository;
    ExpedienteRepository expedienteRepository;
    SolicitudRepository solicitudRepository;
    ResolucionMapper mapper;

    @Override
    public List<ResolucionResponse> listar() {
        return resolucionRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ResolucionResponse obtenerPorId(Integer id) {
        return resolucionRepository.findById(id)
                .map(mapper::toResponse).orElse(null);
    }

    @Override
    public ResolucionResponse guardar(ResolucionRequest resolucionRequest) {
        ResolucionModel model = mapper.toEntity(resolucionRequest);
        return mapper.toResponse(resolucionRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        resolucionRepository.deleteById(id);
    }

    @Override
    public ResolucionResponse actualizar(Integer id, ResolucionRequest resolucionActualizado) {
        ResolucionModel model = resolucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resolucion no encontrado con id: " + id));
        mapper.updateEntityFromRequest(resolucionActualizado, model);
        return mapper.toResponse(resolucionRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return resolucionRepository.existsById(id);
    }

    @Override
    public List<ResolucionResponse> buscarPorExpediente(Integer idExpediente) {
        return resolucionRepository.findByExpediente_IdExpediente(idExpediente).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public ResolucionResponse buscarPorNumero(String numeroResolucion) {
        return resolucionRepository.findByNumeroResolucion(numeroResolucion).map(mapper:: toResponse).orElse(null);
    }

}
