package com.example.mscursos.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mscursos.model.entity.CatalogoModel;
import com.example.mscursos.model.request.CatalogoRequest;
import com.example.mscursos.model.response.CatalogoResponse;
import com.example.mscursos.repository.CatalogoRepository;
import com.example.mscursos.service.CatalogoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Cacheable(cacheNames = "catalogo", key = "'listar'")
    public List<CatalogoResponse> listar() {
        return catalogoRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo", key = "'id:' + #id")
    public CatalogoResponse obtenerPorId(Integer id) {
        return catalogoRepository.findById(id)
                .map(m -> modelMapper.map(m, CatalogoResponse.class))
                .orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "catalogo", key = "'cat:' + #categoria")
    public List<CatalogoResponse> listarPorCategoria(String categoria) {
        return catalogoRepository.findByCategoria(categoria)
                .stream()
                .map(m -> modelMapper.map(m, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo", key = "'estado:' + #estado")
    public List<CatalogoResponse> listarPorEstado(Boolean estado) {
        return catalogoRepository.findByEstado(estado)
                .stream()
                .map(m -> modelMapper.map(m, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo", key = "'cat:' + #categoria + ':estado:' + #estado")
    public List<CatalogoResponse> listarPorCategoriaYEstado(String categoria, Boolean estado) {
        return catalogoRepository.findByCategoriaAndEstado(categoria, estado)
                .stream()
                .map(m -> modelMapper.map(m, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "catalogo", key = "'padre:' + #idPadre")
    public List<CatalogoResponse> listarHijos(String idPadre) {
        return catalogoRepository.findByIdPadre(idPadre)
                .stream()
                .map(m -> modelMapper.map(m, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "catalogo", allEntries = true)
    public CatalogoResponse guardar(CatalogoRequest request) {
        CatalogoModel model = modelMapper.map(request, CatalogoModel.class);

        // si no envían estado, default true
        if (model.getEstado() == null)
            model.setEstado(true);

        CatalogoModel saved = catalogoRepository.save(model);
        return modelMapper.map(saved, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "catalogo", allEntries = true)
    public CatalogoResponse actualizar(Integer id, CatalogoRequest request) {
        CatalogoModel model = catalogoRepository.findById(id).orElse(null);
        if (model == null)
            return null;

        model.setCategoria(request.getCategoria());
        model.setNombre(request.getNombre());
        model.setOrden(request.getOrden());
        model.setIdPadre(request.getIdPadre());
        if (request.getEstado() != null)
            model.setEstado(request.getEstado());

        CatalogoModel saved = catalogoRepository.save(model);
        return modelMapper.map(saved, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "catalogo", allEntries = true)
    public void cambiarEstado(Integer id, Boolean estado) {
        CatalogoModel model = catalogoRepository.findById(id).orElse(null);
        if (model == null)
            return;
        model.setEstado(estado);
        catalogoRepository.save(model);
    }

    @Override
    @CacheEvict(cacheNames = "catalogo", allEntries = true)
    public void eliminar(Integer id) {
        catalogoRepository.deleteById(id);
    }
}
