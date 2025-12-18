package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.Seguimiento;
import com.unu.ms.MsSecretariaAcademica.model.mapper.SeguimientoMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.SeguimientoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SeguimientoResponse;
import com.unu.ms.MsSecretariaAcademica.repository.SeguimientoRepository;
import com.unu.ms.MsSecretariaAcademica.service.SeguimientoService;

@Slf4j
@Service
public class SeguimientoServiceImp implements SeguimientoService {

    SeguimientoRepository seguimientoRepository;
    SeguimientoMapper mapper;

    @Override
    public List<SeguimientoResponse> listar() {
        return seguimientoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public SeguimientoResponse obtenerPorId(Integer id) {
        return seguimientoRepository.findById(id)
                .map(mapper::toResponse).orElse(null);
    }

    @Override
    public SeguimientoResponse guardar(SeguimientoRequest seguimientoRequest) {
        Seguimiento model = mapper.toEntity(seguimientoRequest);
        return mapper.toResponse(seguimientoRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        seguimientoRepository.deleteById(id);
    }

    @Override
    public SeguimientoResponse actualizar(Integer id, SeguimientoRequest seguimientoActualizado) {
        Seguimiento model = seguimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado con id: " + id));
        mapper.updateEntityFromRequest(seguimientoActualizado, model);
        return mapper.toResponse(seguimientoRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return seguimientoRepository.existsById(id);
    }

    @Override
    public List<SeguimientoResponse> buscarPorEntidad(Integer idEntidadCatalogo, Integer entidadId) {
        return seguimientoRepository.findByEntidadCatalogoIdAndEntidadIdOrderByFechaRegistroDesc(idEntidadCatalogo, entidadId).stream().map(mapper::toResponse).toList();
    }

}
