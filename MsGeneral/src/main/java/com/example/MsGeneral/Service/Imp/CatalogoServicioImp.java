package com.example.MsGeneral.Service.Imp;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

import com.example.MsGeneral.Model.Entidad.Catalogo;
import com.example.MsGeneral.Model.Request.CatalogoRequest;
import com.example.MsGeneral.Model.Response.CatalogoResponse;
import com.example.MsGeneral.Repository.CatalogoRepository;
import com.example.MsGeneral.Service.CatalogoService;

@Service
public class CatalogoServicioImp implements CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(
        value = "catalogoListar",
        cacheManager = "listCacheManager"
    )
    public List<CatalogoResponse> listar() {
        return catalogoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(
        value = "catalogoCategoria",
        key = "#categoria",
        cacheManager = "listCacheManager"
    )
    public List<CatalogoResponse> listarPorCategoria(String categoria) {
        return catalogoRepository.findByCategoria(categoria)
                .stream()
                .map(model -> modelMapper.map(model, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(
        value = "catalogoId",
        key = "#id",
        cacheManager = "objectCacheManager",
        unless = "#result == null"
    )
    public CatalogoResponse obtenerPorId(String id) {
        return catalogoRepository.findById(id)
                .map(model -> modelMapper.map(model, CatalogoResponse.class))
                .orElse(null);
    }

    @Override
    @CachePut(
        value = "catalogoId",
        key = "#result.idCatalogo",
        cacheManager = "objectCacheManager"
    )
    @CacheEvict(
        value = { "catalogoListar", "catalogoCategoria" },
        allEntries = true
    )
    public CatalogoResponse guardar(CatalogoRequest request) {

        Catalogo model = modelMapper.map(request, Catalogo.class);

        Integer nuevoCodigo = catalogoRepository
                .findTopByCategoriaOrderByCodigoDesc(request.getCategoria())
                .map(c -> c.getCodigo() + 1)
                .orElse(1);

        model.setCodigo(nuevoCodigo);
        model.setOrden(nuevoCodigo);

        Catalogo saved = catalogoRepository.save(model);

        return modelMapper.map(saved, CatalogoResponse.class);
    }

    @Override
    @CachePut(
        value = "catalogoId",
        key = "#id",
        cacheManager = "objectCacheManager"
    )
    @CacheEvict(
        value = { "catalogoListar", "catalogoCategoria" },
        allEntries = true
    )
    public CatalogoResponse actualizar(String id, CatalogoRequest request) {

        Catalogo modelExistente = catalogoRepository.findById(id).orElse(null);

        modelExistente.setCategoria(request.getCategoria());
        modelExistente.setDescripcion(request.getDescripcion());
        modelExistente.setAbreviatura(request.getAbreviatura());
        modelExistente.setActivo(request.isActivo());

        Catalogo saved = catalogoRepository.save(modelExistente);

        return modelMapper.map(saved, CatalogoResponse.class);
    }

    @Override
    @CacheEvict(
        value = { "catalogoId", "catalogoListar", "catalogoCategoria" },
        allEntries = true
    )
    public void cambiarEstado(String id, boolean activo) {

        Catalogo model = catalogoRepository.findById(id).orElse(null);
        model.setActivo(activo);
        catalogoRepository.save(model);
    }

    @Override
    @CacheEvict(
        value = { "catalogoId", "catalogoListar", "catalogoCategoria" },
        allEntries = true
    )
    public void eliminar(String id) {
        catalogoRepository.deleteById(id);
    }
}
