package com.example.MsPlanEstudios.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.model.entity.CatalogoModel;
import com.example.MsPlanEstudios.model.request.CatalogoRequest;
import com.example.MsPlanEstudios.model.response.CatalogoResponse;
import com.example.MsPlanEstudios.repository.CatalogoRepository;
import com.example.MsPlanEstudios.service.CatalogoService;

@Service
public class CatalogoServiceImp implements CatalogoService{
    @Autowired
    private CatalogoRepository repository;

    @Override
    public CatalogoResponse guardar(CatalogoRequest r) {
        CatalogoModel c = new CatalogoModel();
        c.setCategoria(r.getCategoria());
        c.setCodigo(r.getCodigo());
        c.setDescripcion(r.getDescripcion());
        c.setAbreviatura(r.getAbreviatura());
        c.setEstado(true);
        System.out.println("Categoria: " + r.getCategoria());
        c = repository.save(c);

        return map(c);
    }

    @Override
    public List<CatalogoResponse> listarPorCategoria(String categoria) {
        return repository.findByCategoriaAndEstadoTrue(categoria)
                .stream().map(this::map).toList();
    }

    @Override
    public CatalogoModel obtenerActivo(Integer id) {
        return repository.findById(id)
                .filter(CatalogoModel::getEstado)
                .orElseThrow(() -> new RuntimeException("Catálogo inválido"));
    }

    private CatalogoResponse map(CatalogoModel c) {
        CatalogoResponse r = new CatalogoResponse();
        r.setId(c.getId());
        r.setCategoria(c.getCategoria());
        r.setCodigo(c.getCodigo());
        r.setDescripcion(c.getDescripcion());
        r.setAbreviatura(c.getAbreviatura());
        r.setEstado(c.getEstado());
        return r;
    }
}
