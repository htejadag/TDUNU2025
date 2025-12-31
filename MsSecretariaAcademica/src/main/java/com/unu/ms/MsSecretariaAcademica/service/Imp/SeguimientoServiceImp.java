package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SeguimientoServiceImp implements SeguimientoService {

    SeguimientoRepository seguimientoRepository;
    SeguimientoMapper mapper;

    @Override
    public List<SeguimientoResponse> listar() {

        log.info("Inicio servicio: listar seguimientos");

        List<SeguimientoResponse> resultado =
                seguimientoRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar seguimientos. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public SeguimientoResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener seguimiento por id");
        log.debug("Id seguimiento: {}", id);

        SeguimientoResponse response = seguimientoRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Seguimiento no encontrado. Id: {}", id);
        } else {
            log.info("Seguimiento encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener seguimiento por id");

        return response;
    }

    @Override
    public SeguimientoResponse guardar(SeguimientoRequest seguimientoRequest) {

        log.info("Inicio servicio: guardar seguimiento");
        log.debug("Datos de entrada guardar seguimiento: {}", seguimientoRequest);

        Seguimiento model = mapper.toEntity(seguimientoRequest);

        Seguimiento guardado = seguimientoRepository.save(model);

        log.info("Seguimiento guardado correctamente. Id generado: {}", guardado.getIdSeguimiento());

        log.info("Fin servicio: guardar seguimiento");

        return mapper.toResponse(guardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar seguimiento");
        log.debug("Id seguimiento a eliminar: {}", id);

        seguimientoRepository.deleteById(id);

        log.info("Fin servicio: eliminar seguimiento. Id eliminado: {}", id);
    }

    @Override
    public SeguimientoResponse actualizar(Integer id, SeguimientoRequest seguimientoActualizado) {

        log.info("Inicio servicio: actualizar seguimiento");
        log.debug("Id seguimiento a actualizar: {}", id);
        log.debug("Datos de entrada actualizar seguimiento: {}", seguimientoActualizado);

        Seguimiento model = seguimientoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Seguimiento no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Seguimiento no encontrado con id: " + id);
                });

        mapper.updateEntityFromRequest(seguimientoActualizado, model);

        Seguimiento actualizado = seguimientoRepository.save(model);

        log.info("Seguimiento actualizado correctamente. Id: {}", actualizado.getIdSeguimiento());

        log.info("Fin servicio: actualizar seguimiento");

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de seguimiento. Id: {}", id);

        boolean existe = seguimientoRepository.existsById(id);

        log.debug("Resultado existencia seguimiento. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    @Override
    public List<SeguimientoResponse> buscarPorEntidad(Integer idEntidadCatalogo, Integer entidadId) {

        log.info("Inicio servicio: buscar seguimientos por entidad");
        log.debug("Id entidad catalogo: {}, Entidad id: {}", idEntidadCatalogo, entidadId);

        List<SeguimientoResponse> resultado =
                seguimientoRepository
                        .findByEntidadCatalogoIdAndEntidadIdOrderByFechaRegistroDesc(idEntidadCatalogo, entidadId)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar seguimientos por entidad. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}
