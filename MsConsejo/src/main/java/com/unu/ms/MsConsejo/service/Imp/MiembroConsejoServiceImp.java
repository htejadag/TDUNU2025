package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.MiembroConsejoMapper;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;

@Slf4j
@Service
@AllArgsConstructor
public class MiembroConsejoServiceImp implements MiembroConsejoService {

    MiembroConsejoRepository miembroConsejoRepository;
    AuditoriaServiceImp auditoriaServiceImp;
    MiembroConsejoMapper mapper;

    private static final String NAME_ENTITY = "MIEMBROS_CONSEJO";

    @Override
    public List<MiembroConsejoResponse> listar() {

        log.info("Inicio servicio: listar miembros de consejo");

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: listar miembros de consejo. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    @Override
    public MiembroConsejoResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener miembro de consejo por id");
        log.debug("Id miembro consejo: {}", id);

        MiembroConsejoResponse response = miembroConsejoRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Miembro Consejo no encontrado. Id: {}", id);
        } else {
            log.info("Miembro Consejo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener miembro de consejo por id");

        return response;
    }

    @Override
    public MiembroConsejoResponse guardar(MiembroConsejoRequest miembroConsejoRequest) {

        log.info("Inicio servicio: guardar miembro de consejo");
        log.debug("Datos de entrada guardar miembro consejo: {}", miembroConsejoRequest);

        MiembroConsejoModel model = mapper.toEntity(miembroConsejoRequest);
        MiembroConsejoModel guardado = miembroConsejoRepository.save(model);

        log.info(
                "Miembro Consejo guardado correctamente. Id generado: {}",
                guardado.getIdMiembro()
        );

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdMiembro(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Registro de nuevo Miembro de consejo"
        );

        log.info("Fin servicio: guardar miembro de consejo");

        return mapper.toResponse(guardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar miembro de consejo");
        log.debug("Id miembro consejo a eliminar: {}", id);

        MiembroConsejoModel model = miembroConsejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Miembro Consejo no encontrado para eliminar. Id: {}", id);
                    return new RuntimeException("Miembro Consejo no encontrada");
                });

        miembroConsejoRepository.deleteById(id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de Miembro Consejo"
        );

        log.info(
                "Fin servicio: eliminar miembro de consejo. Id eliminado: {}",
                id
        );
    }

    @Override
    public MiembroConsejoResponse actualizar(
            Integer id,
            MiembroConsejoRequest miembroConsejoActualizado) {

        log.info("Inicio servicio: actualizar miembro de consejo");
        log.debug("Id miembro consejo a actualizar: {}", id);
        log.debug(
                "Datos de entrada actualizar miembro consejo: {}",
                miembroConsejoActualizado
        );

        MiembroConsejoModel model = miembroConsejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Miembro Consejo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException(
                            "Miembro del Consejo no encontrado con id: " + id
                    );
                });

        String antes = model.toString();

        mapper.updateEntityFromRequest(miembroConsejoActualizado, model);

        MiembroConsejoModel actualizado = miembroConsejoRepository.save(model);

        log.info(
                "Miembro Consejo actualizado correctamente. Id: {}",
                actualizado.getIdMiembro()
        );

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdMiembro(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de Miembro Consejo"
        );

        log.info("Fin servicio: actualizar miembro de consejo");

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de miembro consejo. Id: {}", id);

        boolean existe = miembroConsejoRepository.existsById(id);

        log.debug(
                "Resultado existencia miembro consejo. Id: {}, Existe: {}",
                id,
                existe
        );

        return existe;
    }

    @Override
    public List<MiembroConsejoResponse> buscarPorConsejo(Integer idConsejo) {

        log.info("Inicio servicio: buscar miembros por consejo");
        log.debug("Id consejo: {}", idConsejo);

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findByConsejo_IdConsejo(idConsejo)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar miembros por consejo. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    @Override
    public List<MiembroConsejoResponse> buscarPorPersona(Integer idPersona) {

        log.info("Inicio servicio: buscar miembros por persona");
        log.debug("Id persona: {}", idPersona);

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findByIdPersona(idPersona)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar miembros por persona. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    @Override
    public List<MiembroConsejoResponse> buscarPorCargo(Integer idCargo) {

        log.info("Inicio servicio: buscar miembros por cargo");
        log.debug("Id cargo: {}", idCargo);

        List<MiembroConsejoResponse> resultado =
                miembroConsejoRepository.findByIdCargo(idCargo)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar miembros por cargo. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}