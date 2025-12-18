package com.unu.ms.MsConsejo.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
    public List<CatalogoResponse> listar() {
        log.info("Listando todos los catálogos");
        return catalogoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public CatalogoResponse obtenerPorId(Integer id) {
        log.info("Buscando catálogo con id: {}", id);
        return catalogoRepository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {
        log.info("Guardando nuevo catálogo: {}", catalogoRequest.getDescripcionCatalogo());
        CatalogoModel model = mapper.toEntity(catalogoRequest);
        
        // Si tiene padre, lo asignamos
        if (catalogoRequest.getIdPadre() != null) {
            CatalogoModel padre = catalogoRepository.findById(catalogoRequest.getIdPadre())
                    .orElseThrow(() -> new RuntimeException("Catálogo padre no encontrado con id: " + catalogoRequest.getIdPadre()));
            model.setPadre(padre);
        }
        
        return mapper.toResponse(catalogoRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando catálogo con id: {}", id);
        catalogoRepository.deleteById(id);
    }

    @Override
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado) {
        log.info("Actualizando catálogo con id: {}", id);
        CatalogoModel model = catalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado con id: " + id));
        
        mapper.updateEntityFromRequest(catalogoActualizado, model);
        
        // Actualizar el padre si cambió
        if (catalogoActualizado.getIdPadre() != null) {
            CatalogoModel padre = catalogoRepository.findById(catalogoActualizado.getIdPadre())
                    .orElseThrow(() -> new RuntimeException("Catálogo padre no encontrado con id: " + catalogoActualizado.getIdPadre()));
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
    public List<CatalogoResponse> buscarPorDescripcion(String descripcion) {
        log.info("Buscando catálogos por descripción: {}", descripcion);
        return catalogoRepository.findByDescripcionCatalogo(descripcion).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<CatalogoResponse> buscarPorAbreviatura(String abreviatura) {
        log.info("Buscando catálogos por abreviatura: {}", abreviatura);
        return catalogoRepository.findByAbreviaturaCatalogo(abreviatura).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<CatalogoResponse> buscarPorEstado(String estado) {
        log.info("Buscando catálogos por estado: {}", estado);
        return catalogoRepository.findByEstadoCatalogo(estado).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<CatalogoResponse> buscarHijosPorPadre(Integer idPadre) {
        log.info("Buscando catálogos hijos del padre con id: {}", idPadre);
        return catalogoRepository.findByPadre_IdCatalogo(idPadre).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<CatalogoResponse> buscarCatalogosRaiz() {
        log.info("Buscando catálogos raíz (sin padre)");
        return catalogoRepository.findByPadreIsNull().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<CatalogoResponse> buscarPorPadreYEstado(Integer idPadre, String estado) {
        log.info("Buscando catálogos por padre: {} y estado: {}", idPadre, estado);
        return catalogoRepository.findByPadreAndEstado(idPadre, estado).stream().map(mapper::toResponse).toList();
    }

}
