package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.ConsejoMapper;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;

@Slf4j
@Service
@AllArgsConstructor
public class ConsejoServiceImp implements ConsejoService {

    ConsejoRepository consejoRepository;
    AuditoriaServiceImp auditoriaServiceImp;
    ConsejoMapper mapper;

    private static final String NAME_ENTITY = "CONSEJO";

    @Override
    public List<ConsejoResponse> listar() {

        log.info("Inicio servicio: listar consejos");

        List<ConsejoResponse> resultado =
                consejoRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar consejos. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public ConsejoResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener consejo por id");
        log.debug("Id consejo: {}", id);

        ConsejoResponse response = consejoRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Consejo no encontrado. Id: {}", id);
        } else {
            log.info("Consejo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener consejo por id");

        return response;
    }

    @Override
    public ConsejoResponse guardar(ConsejoRequest consejoRequest) {

        log.info("Inicio servicio: guardar consejo");
        log.debug("Datos de entrada guardar consejo: {}", consejoRequest);

        ConsejoModel model = mapper.toEntity(consejoRequest);
        ConsejoModel guardado = consejoRepository.save(model);

        log.info("Consejo guardado correctamente. Id generado: {}", guardado.getIdConsejo());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                guardado.getIdConsejo(),
                "CREATE",
                10,
                null,
                guardado.toString(),
                "Registro de nuevo Consejo"
        );

        log.info("Fin servicio: guardar consejo");

        return mapper.toResponse(guardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar consejo");
        log.debug("Id consejo a eliminar: {}", id);

        ConsejoModel model = consejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Consejo no encontrado para eliminar. Id: {}", id);
                    return new RuntimeException("Consejo no encontrada");
                });

        consejoRepository.deleteById(id);

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                id,
                "DELETE",
                10,
                model.toString(),
                null,
                "Eliminación de Consejo"
        );

        log.info("Fin servicio: eliminar consejo. Id eliminado: {}", id);
    }

    @Override
    public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado) {

        log.info("Inicio servicio: actualizar consejo");
        log.debug("Id consejo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar consejo: {}", consejoActualizado);

        ConsejoModel model = consejoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Consejo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Consejo no encontrado con id: " + id);
                });

        String antes = model.toString();

        mapper.updateEntityFromRequest(consejoActualizado, model);

        ConsejoModel actualizado = consejoRepository.save(model);

        log.info("Consejo actualizado correctamente. Id: {}", actualizado.getIdConsejo());

        auditoriaServiceImp.registrarAccion(
                NAME_ENTITY,
                actualizado.getIdConsejo(),
                "UPDATE",
                10,
                antes,
                actualizado.toString(),
                "Actualización de Consejo"
        );

        log.info("Fin servicio: actualizar consejo");

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de consejo. Id: {}", id);

        boolean existe = consejoRepository.existsById(id);

        log.debug("Resultado existencia consejo. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    @Override
    public ConsejoResponse buscarPorNombre(String nombre) {

        log.info("Inicio servicio: buscar consejo por nombre");
        log.debug("Nombre consejo: {}", nombre);

        ConsejoResponse response = consejoRepository.findByNombre(nombre)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Consejo no encontrado con nombre: {}", nombre);
        }

        log.info("Fin servicio: buscar consejo por nombre");

        return response;
    }

    @Override
    public List<ConsejoResponse> buscarPorEstado(Integer idEstado) {

        log.info("Inicio servicio: buscar consejos por estado");
        log.debug("Id estado: {}", idEstado);

        List<ConsejoResponse> resultado =
                consejoRepository.findByIdEstado(idEstado)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar consejos por estado. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}