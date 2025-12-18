package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;
import com.unu.ms.MsSecretariaAcademica.model.mapper.SolicitudMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.SolicitudRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SolicitudResponse;
import com.unu.ms.MsSecretariaAcademica.repository.SolicitudRepository;
import com.unu.ms.MsSecretariaAcademica.service.SolicitudService;

@Slf4j
@Service
public class SolicitudServiceImp implements SolicitudService {

    SolicitudRepository solicitudRepository;
    SolicitudMapper mapper;

    @Override
    public List<SolicitudResponse> listar() {
        return solicitudRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public SolicitudResponse obtenerPorId(Integer id) {
        return solicitudRepository.findById(id)
                .map(mapper::toResponse).orElse(null);
    }

    @Override
    public SolicitudResponse guardar(SolicitudRequest solicitudRequest) {
        SolicitudModel model = mapper.toEntity(solicitudRequest);
        return mapper.toResponse(solicitudRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        solicitudRepository.deleteById(id);
    }

    @Override
    public SolicitudResponse actualizar(Integer id, SolicitudRequest solicitudActualizado) {
        SolicitudModel model = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado con id: " + id));
        mapper.updateEntityFromRequest(solicitudActualizado, model);
        return mapper.toResponse(solicitudRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return solicitudRepository.existsById(id);
    }

    @Override
    public List<SolicitudResponse> obtenerPorPersona(Integer idPersona) {
        return solicitudRepository.findByIdPersona(idPersona).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public List<SolicitudResponse> obtenerPorEstado(Integer idEstado) {
        return solicitudRepository.findByIdEstado(idEstado).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public List<SolicitudResponse> obtenerPorTipo(Integer idTipoSolicitud) {
        return solicitudRepository.findByIdTipoSolicitud(idTipoSolicitud).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public List<SolicitudResponse> obtenerPorPersonaYEstado(Integer idPersona, Integer idEstado) {
        return solicitudRepository.findByIdPersonaAndIdEstado(idPersona, idEstado).stream().map(mapper:: toResponse).toList();
    }   

    @Override
    public List<SolicitudResponse> obtenerPorTipoYEstado(Integer idTipoSolicitud, Integer idEstado) {
        return solicitudRepository.findByIdTipoSolicitudAndIdEstado(idTipoSolicitud, idEstado).stream().map(mapper:: toResponse).toList();
    }

    @Override
    public List<SolicitudResponse> obtenerPorFechaRango(String fechaInicio, String fechaFin) {
        return solicitudRepository.findByFechaSolicitudBetween(fechaInicio, fechaFin).stream().map(mapper:: toResponse).toList();
    }
    
}
