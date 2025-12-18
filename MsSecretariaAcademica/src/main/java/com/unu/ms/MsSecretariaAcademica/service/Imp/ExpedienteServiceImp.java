package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.ExpedienteModel;
import com.unu.ms.MsSecretariaAcademica.model.mapper.ExpedienteMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.ExpedienteRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ExpedienteResponse;
import com.unu.ms.MsSecretariaAcademica.repository.ExpedienteRepository;
import com.unu.ms.MsSecretariaAcademica.service.ExpedienteService;

@Slf4j
@Service
public class ExpedienteServiceImp implements ExpedienteService {

    ExpedienteRepository expedienteRepository;
    ExpedienteMapper mapper;

    @Override
    public List<ExpedienteResponse> listar() {
        return expedienteRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ExpedienteResponse obtenerPorId(Integer id) {
        return expedienteRepository.findById(id)
                .map(mapper::toResponse).orElse(null);
    }

    @Override
    public ExpedienteResponse guardar(ExpedienteRequest expedienteRequest) {
        ExpedienteModel model = mapper.toEntity(expedienteRequest);
        return mapper.toResponse(expedienteRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        expedienteRepository.deleteById(id);
    }

    @Override
    public ExpedienteResponse actualizar(Integer id, ExpedienteRequest expedienteActualizado) {
        ExpedienteModel model = expedienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado con id: " + id));
        mapper.updateEntityFromRequest(expedienteActualizado, model);
        return mapper.toResponse(expedienteRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return expedienteRepository.existsById(id);
    }

    @Override
    public ExpedienteResponse buscarPorCodigo(String codigo) {
        return expedienteRepository.findByCodigoExpediente(codigo).map(mapper:: toResponse).orElse(null);
    }

    @Override
    public List<ExpedienteResponse> buscarPorPersona(Integer idPersona) {
        return expedienteRepository.findByIdPersona(idPersona).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public List<ExpedienteResponse> buscarPorPersonaYEstado(Integer idPersona, Integer idEstado) {
        return expedienteRepository.findByIdPersonaAndIdEstado(idPersona, idEstado).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public List<ExpedienteResponse> buscarPorEstado(Integer idEstado) {
        return expedienteRepository.findByIdEstado(idEstado).stream().map(mapper:: toResponse).toList();
    }

}
