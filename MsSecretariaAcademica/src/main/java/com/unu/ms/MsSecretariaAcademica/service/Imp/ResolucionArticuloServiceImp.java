package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionArticuloModel;
import com.unu.ms.MsSecretariaAcademica.model.mapper.ResolucionArticuloMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsSecretariaAcademica.repository.ResolucionArticuloRepository;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionArticuloService;

@Slf4j
@Service
@AllArgsConstructor
public class ResolucionArticuloServiceImp implements ResolucionArticuloService {

    ResolucionArticuloRepository resolucionArticuloRepository;
    ResolucionArticuloMapper mapper;

    @Override
    public List<ResolucionArticuloResponse> listar() {

        log.info("Inicio servicio: listar resolucion articulos");

        List<ResolucionArticuloResponse> resultado =
                resolucionArticuloRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar resolucion articulos. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    public ResolucionArticuloResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener resolucion articulo por id");
        log.debug("Id resolucion articulo: {}", id);

        ResolucionArticuloResponse response = resolucionArticuloRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Resolucion articulo no encontrado. Id: {}", id);
        } else {
            log.info("Resolucion articulo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener resolucion articulo por id");

        return response;
    }

    @Override
    public ResolucionArticuloResponse guardar(ResolucionArticuloRequest resolucionArticuloRequest) {

        log.info("Inicio servicio: guardar resolucion articulo");
        log.debug("Datos de entrada guardar resolucion articulo: {}", resolucionArticuloRequest);

        ResolucionArticuloModel model = mapper.toEntity(resolucionArticuloRequest);

        log.debug("Entidad resolucion articulo mapeada correctamente");

        ResolucionArticuloModel guardado = resolucionArticuloRepository.save(model);

        log.info("Resolucion articulo guardado correctamente. Id generado: {}", guardado.getIdArticulo());

        return mapper.toResponse(guardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar resolucion articulo");
        log.debug("Id resolucion articulo a eliminar: {}", id);

        resolucionArticuloRepository.deleteById(id);

        log.info("Fin servicio: eliminar resolucion articulo. Id eliminado: {}", id);
    }

    @Override
    public ResolucionArticuloResponse actualizar(Integer id, ResolucionArticuloRequest resolucionArticuloActualizado) {

        log.info("Inicio servicio: actualizar resolucion articulo");
        log.debug("Id resolucion articulo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar resolucion articulo: {}", resolucionArticuloActualizado);

        ResolucionArticuloModel model = resolucionArticuloRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Resolucion articulo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Resolucion Articulo no encontrado con id: " + id);
                });

        log.debug("Resolucion articulo encontrado. Aplicando cambios");

        mapper.updateEntityFromRequest(resolucionArticuloActualizado, model);

        ResolucionArticuloModel actualizado = resolucionArticuloRepository.save(model);

        log.info("Resolucion articulo actualizado correctamente. Id: {}", actualizado.getIdArticulo());

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de resolucion articulo. Id: {}", id);

        boolean existe = resolucionArticuloRepository.existsById(id);

        log.debug("Resultado existencia resolucion articulo. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    @Override
    public List<ResolucionArticuloResponse> buscarPorResolucion(Integer idResolucion) {

        log.info("Inicio servicio: buscar resolucion articulos por resolucion");
        log.debug("Id resolucion: {}", idResolucion);

        List<ResolucionArticuloResponse> resultado =
                resolucionArticuloRepository
                        .findByResolucion_IdResolucionOrderByNumeroArticuloAsc(idResolucion)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar resolucion articulos por resolucion. Total registros: {}", resultado.size());

        return resultado;
    }

}
