package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ExpedienteServiceImp implements ExpedienteService {

    ExpedienteRepository expedienteRepository;

    AuditoriaServiceImp auditoriaServiceImp;
    
    ExpedienteMapper mapper;

    private static final String NAME_ENTITY = "EXPEDIENTE";

    @Override
    public List<ExpedienteResponse> listar() {

        log.info("Inicio servicio: listar expedientes");

        List<ExpedienteResponse> resultado =
                expedienteRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar expedientes. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public ExpedienteResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener expediente por id");
        log.debug("Id expediente: {}", id);

        ExpedienteResponse response = expedienteRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Expediente no encontrado. Id: {}", id);
        } else {
            log.info("Expediente encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener expediente por id");

        return response;
    }

    @Override
    public ExpedienteResponse guardar(ExpedienteRequest expedienteRequest) {

        log.info("Inicio servicio: guardar expediente");
        log.debug("Datos de entrada guardar expediente: {}", expedienteRequest);

        ExpedienteModel model = mapper.toEntity(expedienteRequest);

        log.debug("Entidad expediente mapeada correctamente");

        ExpedienteModel guardado = expedienteRepository.save(model);

        log.info("Expediente guardado correctamente. Id generado: {}", guardado.getIdExpediente());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdExpediente(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Creación de expediente");

        log.debug("Auditoria registrada para creación de expediente. Id: {}", guardado.getIdExpediente());

        return mapper.toResponse(guardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar expediente");
        log.debug("Id expediente a eliminar: {}", id);

        ExpedienteModel model = expedienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Expediente no encontrado para eliminar. Id: {}", id);
                    return new RuntimeException("Expediente no encontrada");
                });

        expedienteRepository.deleteById(id);

        log.info("Expediente eliminado correctamente. Id: {}", id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de Expediente");

        log.debug("Auditoria registrada para eliminación de expediente. Id: {}", id);
    }

    @Override
    public ExpedienteResponse actualizar(Integer id, ExpedienteRequest expedienteActualizado) {

        log.info("Inicio servicio: actualizar expediente");
        log.debug("Id expediente a actualizar: {}", id);
        log.debug("Datos de entrada actualizar expediente: {}", expedienteActualizado);

        ExpedienteModel model = expedienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Expediente no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Expediente no encontrado con id: " + id);
                });

        String antes = model.toString();

        log.debug("Estado anterior del expediente obtenido");

        mapper.updateEntityFromRequest(expedienteActualizado, model);

        ExpedienteModel actualizado = expedienteRepository.save(model);

        log.info("Expediente actualizado correctamente. Id: {}", actualizado.getIdExpediente());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdExpediente(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de Expediente");

        log.debug("Auditoria registrada para actualización de expediente. Id: {}", actualizado.getIdExpediente());

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de expediente. Id: {}", id);

        boolean existe = expedienteRepository.existsById(id);

        log.debug("Resultado existencia expediente. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    @Override
    public ExpedienteResponse buscarPorCodigo(String codigo) {

        log.info("Inicio servicio: buscar expediente por codigo");
        log.debug("Codigo expediente: {}", codigo);

        ExpedienteResponse response = expedienteRepository.findByCodigoExpediente(codigo)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("No se encontro expediente para el codigo: {}", codigo);
        }

        log.info("Fin servicio: buscar expediente por codigo");

        return response;
    }

    @Override
    public List<ExpedienteResponse> buscarPorPersona(Integer idPersona) {

        log.info("Inicio servicio: buscar expedientes por persona");
        log.debug("Id persona: {}", idPersona);

        List<ExpedienteResponse> resultado =
                expedienteRepository.findByIdPersona(idPersona)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar expedientes por persona. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public List<ExpedienteResponse> buscarPorPersonaYEstado(Integer idPersona, Integer idEstado) {

        log.info("Inicio servicio: buscar expedientes por persona y estado");
        log.debug("Id persona: {}, Id estado: {}", idPersona, idEstado);

        List<ExpedienteResponse> resultado =
                expedienteRepository.findByIdPersonaAndIdEstado(idPersona, idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar expedientes por persona y estado. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public List<ExpedienteResponse> buscarPorEstado(Integer idEstado) {

        log.info("Inicio servicio: buscar expedientes por estado");
        log.debug("Id estado: {}", idEstado);

        List<ExpedienteResponse> resultado =
                expedienteRepository.findByIdEstado(idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar expedientes por estado. Total registros: {}", resultado.size());

        return resultado;
    }

}
