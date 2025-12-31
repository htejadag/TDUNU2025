package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SolicitudServiceImp implements SolicitudService {

    SolicitudRepository solicitudRepository;

    AuditoriaServiceImp auditoriaServiceImp;
    
    SolicitudMapper mapper;

    private static final String NAME_ENTITY = "SOLICITUD";

    @Override
    public List<SolicitudResponse> listar() {

        log.info("Inicio servicio: listar solicitudes");

        List<SolicitudResponse> resultado =
                solicitudRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar solicitudes. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public SolicitudResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener solicitud por id");
        log.debug("Id solicitud: {}", id);

        SolicitudResponse response = solicitudRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Solicitud no encontrada. Id: {}", id);
        } else {
            log.info("Solicitud encontrada. Id: {}", id);
        }

        log.info("Fin servicio: obtener solicitud por id");

        return response;
    }

    @Override
    public SolicitudResponse guardar(SolicitudRequest solicitudRequest) {

        log.info("Inicio servicio: guardar solicitud");
        log.debug("Datos de entrada guardar solicitud: {}", solicitudRequest);

        SolicitudModel model = mapper.toEntity(solicitudRequest);

        SolicitudModel guardado = solicitudRepository.save(model);

        log.info("Solicitud guardada correctamente. Id generado: {}", guardado.getIdSolicitud());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdSolicitud(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Registro de nueva solicitud");

        log.info("Fin servicio: guardar solicitud");

        return mapper.toResponse(guardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar solicitud");
        log.debug("Id solicitud a eliminar: {}", id);

        SolicitudModel model = solicitudRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Solicitud no encontrada para eliminar. Id: {}", id);
                    return new RuntimeException("Solicitud no encontrada");
                });

        solicitudRepository.deleteById(id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de solicitud");

        log.info("Fin servicio: eliminar solicitud. Id eliminado: {}", id);
    }

    @Override
    public SolicitudResponse actualizar(Integer id, SolicitudRequest solicitudActualizado) {

        log.info("Inicio servicio: actualizar solicitud");
        log.debug("Id solicitud a actualizar: {}", id);
        log.debug("Datos de entrada actualizar solicitud: {}", solicitudActualizado);

        SolicitudModel model = solicitudRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Solicitud no encontrada para actualizar. Id: {}", id);
                    return new RuntimeException("Solicitud no encontrado con id: " + id);
                });

        String antes = model.toString();

        mapper.updateEntityFromRequest(solicitudActualizado, model);

        SolicitudModel actualizado = solicitudRepository.save(model);

        log.info("Solicitud actualizada correctamente. Id: {}", actualizado.getIdSolicitud());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdSolicitud(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de solicitud");

        log.info("Fin servicio: actualizar solicitud");

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de solicitud. Id: {}", id);

        boolean existe = solicitudRepository.existsById(id);

        log.debug("Resultado existencia solicitud. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    @Override
    public List<SolicitudResponse> obtenerPorPersona(Integer idPersona) {

        log.info("Inicio servicio: obtener solicitudes por persona");
        log.debug("Id persona: {}", idPersona);

        List<SolicitudResponse> resultado =
                solicitudRepository.findByIdPersona(idPersona)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: obtener solicitudes por persona. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public List<SolicitudResponse> obtenerPorEstado(Integer idEstado) {

        log.info("Inicio servicio: obtener solicitudes por estado");
        log.debug("Id estado: {}", idEstado);

        List<SolicitudResponse> resultado =
                solicitudRepository.findByIdEstado(idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: obtener solicitudes por estado. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public List<SolicitudResponse> obtenerPorTipo(Integer idTipoSolicitud) {

        log.info("Inicio servicio: obtener solicitudes por tipo");
        log.debug("Id tipo solicitud: {}", idTipoSolicitud);

        List<SolicitudResponse> resultado =
                solicitudRepository.findByIdTipoSolicitud(idTipoSolicitud)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: obtener solicitudes por tipo. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public List<SolicitudResponse> obtenerPorPersonaYEstado(Integer idPersona, Integer idEstado) {

        log.info("Inicio servicio: obtener solicitudes por persona y estado");
        log.debug("Id persona: {}, Id estado: {}", idPersona, idEstado);

        List<SolicitudResponse> resultado =
                solicitudRepository.findByIdPersonaAndIdEstado(idPersona, idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: obtener solicitudes por persona y estado. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    @Override
    public List<SolicitudResponse> obtenerPorTipoYEstado(Integer idTipoSolicitud, Integer idEstado) {

        log.info("Inicio servicio: obtener solicitudes por tipo y estado");
        log.debug("Id tipo solicitud: {}, Id estado: {}", idTipoSolicitud, idEstado);

        List<SolicitudResponse> resultado =
                solicitudRepository.findByIdTipoSolicitudAndIdEstado(idTipoSolicitud, idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: obtener solicitudes por tipo y estado. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    @Override
    public List<SolicitudResponse> obtenerPorFechaRango(String fechaInicio, String fechaFin) {

        log.info("Inicio servicio: obtener solicitudes por rango de fechas");
        log.debug("Fecha inicio: {}, Fecha fin: {}", fechaInicio, fechaFin);

        List<SolicitudResponse> resultado =
                solicitudRepository.findByFechaSolicitudBetween(fechaInicio, fechaFin)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: obtener solicitudes por rango de fechas. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}
