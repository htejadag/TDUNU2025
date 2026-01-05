package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
public class CatalogoServiceImp implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final CatalogoMapper mapper;

    @Override
    @Cacheable(cacheNames = "catalogo:list")
    public List<CatalogoResponse> listar() {
        log.info("Listando todos los catalogos");
        return catalogoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo:byId", key = "#id")
    public CatalogoResponse obtenerPorId(Integer id) {
        log.info("Buscando catalogo con id: {}", id);
        return catalogoRepository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    @CacheEvict(cacheNames = {
            "catalogo:list",
            "catalogo:byId",
            "catalogo:descripcion",
            "catalogo:abreviatura",
            "catalogo:estado",
            "catalogo:hijos",
            "catalogo:raiz",
            "catalogo:padreEstado"
    }, allEntries = true)

    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {
        log.info("Guardando nuevo catalogo: {}", catalogoRequest.getDescripcionCatalogo());
        CatalogoModel model = mapper.toEntity(catalogoRequest);
        
        // Si tiene padre, lo asignamos
        if (catalogoRequest.getIdPadre() != null) {
            CatalogoModel padre = catalogoRepository.findById(catalogoRequest.getIdPadre())
                    .orElseThrow(() -> new RuntimeException("Catalogo padre no encontrado con id: " + catalogoRequest.getIdPadre()));
            model.setPadre(padre);
        }
        
        return mapper.toResponse(catalogoRepository.save(model));
    }

    @Override
    @CacheEvict(cacheNames = {
            "catalogo:list",
            "catalogo:byId",
            "catalogo:descripcion",
            "catalogo:abreviatura",
            "catalogo:estado",
            "catalogo:hijos",
            "catalogo:raiz",
            "catalogo:padreEstado"
    }, allEntries = true)



    public void eliminar(Integer id) {
        log.info("Eliminando catalogo con id: {}", id);
        catalogoRepository.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = {
            "catalogo:list",
            "catalogo:byId",
            "catalogo:descripcion",
            "catalogo:abreviatura",
            "catalogo:estado",
            "catalogo:hijos",
            "catalogo:raiz",
            "catalogo:padreEstado"
    }, allEntries = true)

    
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado) {
        log.info("Actualizando catalogo con id: {}", id);
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
        
        return mapper.toResponse(catalogoRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return catalogoRepository.existsById(id);
    }

    @Override
    @Cacheable(cacheNames = "catalogo:descripcion", key = "#descripcion")
    public List<CatalogoResponse> buscarPorDescripcion(String descripcion) {
        log.info("Buscando catalogos por descripción: {}", descripcion);
        return catalogoRepository.findByDescripcionCatalogo(descripcion).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo:abreviatura", key = "#abreviatura")
    public List<CatalogoResponse> buscarPorAbreviatura(String abreviatura) {
        log.info("Buscando catalogos por abreviatura: {}", abreviatura);
        return catalogoRepository.findByAbreviaturaCatalogo(abreviatura).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo:estado", key = "#estado")
    public List<CatalogoResponse> buscarPorEstado(String estado) {
        log.info("Buscando catalogos por estado: {}", estado);
        return catalogoRepository.findByEstadoCatalogo(estado).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo:hijos", key = "#idPadre")
    public List<CatalogoResponse> buscarHijosPorPadre(Integer idPadre) {
        log.info("Buscando catalogos hijos del padre con id: {}", idPadre);
        return catalogoRepository.findByPadre_IdCatalogo(idPadre).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo:raiz")
    public List<CatalogoResponse> buscarCatalogosRaiz() {
        log.info("Buscando catalogos raíz (sin padre)");
        return catalogoRepository.findByPadreIsNull().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo:padreEstado", key = "#idPadre + '::' + #estado")
    public List<CatalogoResponse> buscarPorPadreYEstado(Integer idPadre, String estado) {
        log.info("Buscando catalogos por padre: {} y estado: {}", idPadre, estado);
        return catalogoRepository.findByPadreAndEstado(idPadre, estado).stream().map(mapper::toResponse).toList();
    }

}
