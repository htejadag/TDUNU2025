package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.Catalogo;
import com.unu.ms.MsConsejo.model.mapper.CatalogoMapper;
import com.unu.ms.MsConsejo.model.request.CatalogoRequest;
import com.unu.ms.MsConsejo.model.response.CatalogoResponse;
import com.unu.ms.MsConsejo.repository.CatalogoRepository;
import com.unu.ms.MsConsejo.service.CatalogoService;

@Slf4j
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "ms-consejo:catalogo")
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

        log.info(
                "Fin servicio: listar catalogos. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

    @Override
    @Cacheable(
        key = "'id:' + #id",
        unless = "#result == null"
    )
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
        Catalogo guardado = catalogoRepository.save(model);

        log.info(
                "Catalogo guardado correctamente. Id generado: {}",
                guardado.getIdCatalogo()
        );

        log.info("Fin servicio: guardar catalogo");

        return mapper.toResponse(guardado);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(key = "'id:' + #id"),
        @CacheEvict(key = "'all'"),
        @CacheEvict(key = "'categoria:*'", allEntries = true)
    })
    public void eliminar(Integer id) {

        log.info("Inicio servicio: eliminar catalogo");
        log.debug("Id catalogo a eliminar: {}", id);

        catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Catalogo no encontrado para eliminar. Id: {}", id);
                    return new RuntimeException("Catalogo no encontrado");
                });

        catalogoRepository.deleteById(id);

        log.info(
                "Fin servicio: eliminar catalogo. Id eliminado: {}",
                id
        );
    }

    @Override
    @Caching(
        put = @CachePut(key = "'id:' + #id"),
        evict = {
            @CacheEvict(key = "'all'"),
            @CacheEvict(key = "'categoria:*'", allEntries = true)
        }
    )
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado) {

        log.info("Inicio servicio: actualizar catalogo");
        log.debug("Id catalogo a actualizar: {}", id);
        log.debug("Datos de entrada actualizar catalogo: {}", catalogoActualizado);

        Catalogo model = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Catalogo no encontrado para actualizar. Id: {}", id);
                    return new RuntimeException("Catalogo no encontrado con id: " + id);
                });

        mapper.updateEntityFromRequest(catalogoActualizado, model);

        Catalogo actualizado = catalogoRepository.save(model);

        log.info(
                "Catalogo actualizado correctamente. Id: {}",
                actualizado.getIdCatalogo()
        );

        log.info("Fin servicio: actualizar catalogo");

        return mapper.toResponse(actualizado);
    }

    @Override
    public boolean existePorId(Integer id) {

        log.debug("Validando existencia de catalogo. Id: {}", id);

        boolean existe = catalogoRepository.existsById(id);

        log.debug(
                "Resultado existencia catalogo. Id: {}, Existe: {}",
                id,
                existe
        );

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
            log.warn(
                    "Catalogo no encontrado. Categoria: {}, Valor: {}",
                    categoria,
                    valor
            );
        } else {
            log.info(
                    "Catalogo encontrado. Categoria: {}, Valor: {}",
                    categoria,
                    valor
            );
        }

        log.info("Fin servicio: buscar catalogo por categoria y valor");

        return response;
    }

    @Override
    @Cacheable(key = "'catalogos:categoria:' + #categoria", unless = "#result == null || #result.isEmpty()")
    public List<CatalogoResponse> buscarPorCategoria(String categoria) {

        log.info("Inicio servicio: buscar catalogos por categoria");
        log.debug("Categoria: {}", categoria);

        List<CatalogoResponse> resultado =
                catalogoRepository.findByCategoria(categoria)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        log.info(
                "Fin servicio: buscar catalogos por categoria. Total registros: {}",
                resultado.size()
        );

        return resultado;
    }

}