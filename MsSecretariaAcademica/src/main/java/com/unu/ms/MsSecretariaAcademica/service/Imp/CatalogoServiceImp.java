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
        return catalogoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Cacheable(key = "'catalogo:' + #id", unless = "#result == null")
    public CatalogoResponse obtenerPorId(Integer id) {
        return catalogoRepository.findById(id)
                .map(mapper::toResponse).orElse(null);
    }

    @Override
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {
        Catalogo model = mapper.toEntity(catalogoRequest);
        return mapper.toResponse(catalogoRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
        catalogoRepository.deleteById(id);
    }

    @Override
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado) {
        Catalogo model = catalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalogo no encontrado con id: " + id));
        mapper.updateEntityFromRequest(catalogoActualizado, model);
        return mapper.toResponse(catalogoRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
        return catalogoRepository.existsById(id);
    }

    @Override
    public CatalogoResponse buscarPorCategoriaYValor(String categoria, String valor) {
        return catalogoRepository.findByCategoriaAndValor(categoria, valor).map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<CatalogoResponse> buscarPorCategoria(String categoria) {
        return catalogoRepository.findByCategoria(categoria).stream().map(mapper::toResponse).toList();
    }

}