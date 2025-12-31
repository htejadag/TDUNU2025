package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.Catalogo;
import com.unu.ms.MsSecretariaAcademica.model.mapper.CatalogoMapper;
import com.unu.ms.MsSecretariaAcademica.model.request.CatalogoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.CatalogoResponse;
import com.unu.ms.MsSecretariaAcademica.repository.CatalogoRepository;
import com.unu.ms.MsSecretariaAcademica.service.CatalogoService;

@Slf4j
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "catalogo")
public class CatalogoServiceImp implements CatalogoService {

    CatalogoRepository catalogoRepository;
    CatalogoMapper mapper;

    @Override
    public List<CatalogoResponse> listar() {

        log.info("Inicio servicio: listar catalogos");

        List<CatalogoResponse> resultado =
                catalogoRepository.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: listar catalogos. Total registros: {}", resultado.size());

        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:' + #id", unless = "#result == null")
    public CatalogoResponse obtenerPorId(Integer id) {

        log.info("Inicio servicio: obtener catalogo por id");
        log.debug("Id catalogo: {}", id);

        CatalogoResponse response = catalogoRepository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);

        if (response == null) {
            log.warn("Catalogo no encontrado. Id: {}", id);
        } else {
            log.info("Catalogo encontrado. Id: {}", id);
        }

        log.info("Fin servicio: obtener catalogo por id");

        return response;
    }

    @Override
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {

        log.info("Inicio servicio: guardar catalogo");
        log.debug("Datos de entrada guardar catalogo: {}", catalogoRequest);

        Catalogo model = mapper.toEntity(catalogoRequest);

        log.debug("Entidad catalogo mapeada correctamente");

        Catalogo modelGuardado = catalogoRepository.save(model);

        log.info("Catalogo guardado correctamente. Id generado: {}", modelGuardado.getIdCatalogo());

        return mapper.toResponse(modelGuardado);
    }

    @Override
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar catalogo");
        log.debug("Id catalogo a eliminar: {}", id);

        catalogoRepository.deleteById(id);

        log.info("Fin servicio: eliminar catalogo. Id eliminado: {}", id);
    }

    @Override
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado) {

        log.info("Inicio servicio: actualizar catalogo");
        log.debug("Id catalogo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar catalogo: {}", catalogoActualizado);

        Catalogo model = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Catalogo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Catalogo no encontrado con id: " + id);
                });

        log.debug("Catalogo encontrado. Aplicando cambios");

        mapper.updateEntityFromRequest(catalogoActualizado, model);

        Catalogo modelActualizado = catalogoRepository.save(model);

        log.info("Catalogo actualizado correctamente. Id: {}", id);

        return mapper.toResponse(modelActualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de catalogo. Id: {}", id);

        boolean existe = catalogoRepository.existsById(id);

        log.debug("Resultado existencia catalogo. Id: {}, Existe: {}", id, existe);

        return existe;
    }

    @Override
    public CatalogoResponse buscarPorCategoriaYValor(String categoria, String valor) {

        log.info("Inicio servicio: buscar catalogo por categoria y valor");
        log.debug("Categoria: {}, Valor: {}", categoria, valor);

        CatalogoResponse response =
                catalogoRepository.findByCategoriaAndValor(categoria, valor)
                        .map(mapper::toResponse)
                        .orElse(null);

        if (response == null) {
            log.warn("No se encontro catalogo para categoria y valor");
        }

        log.info("Fin servicio: buscar catalogo por categoria y valor");

        return response;
    }

    @Override
    public List<CatalogoResponse> buscarPorCategoria(String categoria) {

        log.info("Inicio servicio: buscar catalogos por categoria");
        log.debug("Categoria: {}", categoria);

        List<CatalogoResponse> resultado =
                catalogoRepository.findByCategoria(categoria)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info("Fin servicio: buscar catalogos por categoria. Total registros: {}", resultado.size());

        return resultado;
    }

}