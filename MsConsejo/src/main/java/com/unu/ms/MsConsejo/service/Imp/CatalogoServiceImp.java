package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.CatalogoModel;
import com.unu.ms.MsConsejo.model.mapper.CatalogoMapper;
import com.unu.ms.MsConsejo.model.request.CatalogoRequest;
import com.unu.ms.MsConsejo.model.response.CatalogoResponse;
import com.unu.ms.MsConsejo.repository.CatalogoRepository;
import com.unu.ms.MsConsejo.service.CatalogoService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "catalogo")
public class CatalogoServiceImp implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final CatalogoMapper mapper;

    @Override
    @Cacheable(key = "'catalogo:list'", unless = "#result.isEmpty()")
    public List<CatalogoResponse> listar() {
        log.info("Inicio servicio: listar todos los catalogos");
        List<CatalogoResponse> resultado = catalogoRepository.findAll().stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: listar todos los catalogos");
        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:' + #id", unless = "#result == null")
    public CatalogoResponse obtenerPorId(Integer id) {
        log.info("Inicio servicio: obtener catalogo por id: {}", id);
        CatalogoResponse resultado = catalogoRepository.findById(id).map(mapper::toResponse).orElse(null);
        log.info("Fin servicio: obtener catalogo por id");
        return resultado;
    }

    @Override
  
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {
        log.info("Inicio servicio: guardar nuevo catalogo: {}", catalogoRequest.getDescripcionCatalogo());
        CatalogoModel model = mapper.toEntity(catalogoRequest);
        
        // Si tiene padre, lo asignamos
        if (catalogoRequest.getIdPadre() != null) {
            CatalogoModel padre = catalogoRepository.findById(catalogoRequest.getIdPadre())
                    .orElseThrow(() -> new RuntimeException("Catalogo padre no encontrado con id: " + catalogoRequest.getIdPadre()));
            model.setPadre(padre);
        }
        
        CatalogoResponse resultado = mapper.toResponse(catalogoRepository.save(model));
        return resultado;
    }

    @Override
    @CacheEvict(key = "'catalogo:list'", allEntries = false)
    public void eliminar(Integer id) {
        log.info("Inicio servicio: eliminar catalogo con id: {}", id);
        catalogoRepository.deleteById(id);
        log.info("Fin servicio: eliminar catalogo");
    }

    @Override
    @CacheEvict(key = "'catalogo:list'", allEntries = false)
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado) {
        log.info("Inicio servicio: actualizar catalogo con id: {}", id);
        CatalogoModel model = catalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalogo no encontrado con id: " + id));
        
        mapper.updateEntityFromRequest(catalogoActualizado, model);
        
        // Actualizar el padre si cambió (0 o null => sin padre)
        Integer idPadre = catalogoActualizado.getIdPadre();
        if (idPadre != null && idPadre > 0) {
            if (idPadre.equals(id)) {
                throw new RuntimeException("Un catálogo no puede ser su propio padre");
            }
            CatalogoModel padre = catalogoRepository.findById(idPadre)
                    .orElseThrow(() -> new RuntimeException("Catalogo padre no encontrado con id: " + idPadre));
            model.setPadre(padre);
        } else {
            model.setPadre(null);
        }
        
        CatalogoResponse resultado = mapper.toResponse(catalogoRepository.save(model));
        log.info("Fin servicio: actualizar catalogo");
        return resultado;
    }

    @Override
    public boolean existePorId(Integer id) {
        return catalogoRepository.existsById(id);
    }

    @Override
    @Cacheable(key = "'catalogo:desc:' + #descripcion", unless = "#result.isEmpty()")
    public List<CatalogoResponse> buscarPorDescripcion(String descripcion) {
        log.info("Inicio servicio: buscar catalogos por descripción: {}", descripcion);
        List<CatalogoResponse> resultado = catalogoRepository.findByDescripcionCatalogo(descripcion).stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: buscar catalogos por descripción");
        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:abrev:' + #abreviatura", unless = "#result.isEmpty()")
    public List<CatalogoResponse> buscarPorAbreviatura(String abreviatura) {
        log.info("Inicio servicio: buscar catalogos por abreviatura: {}", abreviatura);
        List<CatalogoResponse> resultado = catalogoRepository.findByAbreviaturaCatalogo(abreviatura).stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: buscar catalogos por abreviatura");
        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:estado:' + #estado", unless = "#result.isEmpty()")
    public List<CatalogoResponse> buscarPorEstado(String estado) {
        log.info("Inicio servicio: buscar catalogos por estado: {}", estado);
        List<CatalogoResponse> resultado = catalogoRepository.findByEstadoCatalogo(estado).stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: buscar catalogos por estado");
        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:hijos:' + #idPadre", unless = "#result.isEmpty()")
    public List<CatalogoResponse> buscarHijosPorPadre(Integer idPadre) {
        log.info("Inicio servicio: buscar catalogos hijos del padre con id: {}", idPadre);
        List<CatalogoResponse> resultado = catalogoRepository.findByPadre_IdCatalogo(idPadre).stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: buscar catalogos hijos");
        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:raiz'", unless = "#result.isEmpty()")
    public List<CatalogoResponse> buscarCatalogosRaiz() {
        log.info("Inicio servicio: buscar catalogos raíz (sin padre)");
        List<CatalogoResponse> resultado = catalogoRepository.findByPadreIsNull().stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: buscar catalogos raíz");
        return resultado;
    }

    @Override
    @Cacheable(key = "'catalogo:padre:estado:' + #idPadre + ':' + #estado", unless = "#result.isEmpty()")
    public List<CatalogoResponse> buscarPorPadreYEstado(Integer idPadre, String estado) {
        log.info("Inicio servicio: buscar catalogos por padre: {} y estado: {}", idPadre, estado);
        List<CatalogoResponse> resultado = catalogoRepository.findByPadreAndEstado(idPadre, estado).stream().map(mapper::toResponse).toList();
        log.info("Fin servicio: buscar catalogos por padre y estado");
        return resultado;
    }

}
