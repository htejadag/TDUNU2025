package com.example.MsCuenta.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CatalogoModel;
import com.example.MsCuenta.model.request.Catalogo.CatalogoRequest;
import com.example.MsCuenta.model.response.CatalogoResponse;
import com.example.MsCuenta.repository.CatalogoRepository;
import com.example.MsCuenta.service.CatalogoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CatalogoServiceImpl implements CatalogoService {

    
    @Autowired
    CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "catalogos")
    public List<CatalogoResponse> listar() {

         return catalogoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, CatalogoResponse.class))
            .toList();
       
    }

    @Override
    @Cacheable(value = "catalogo", key = "#id")
    public CatalogoResponse obtenerPorId(Integer id) {

        return catalogoRepository.findById(id)
            .map(model -> modelMapper.map(model, CatalogoResponse.class))
            .orElse(null);
        
    }

    @CacheEvict(value = "catalogos", allEntries = true)
    @Override
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {

        CatalogoModel model = new CatalogoModel();

        model.setTipo(catalogoRequest.getTipo());
        model.setActivo(catalogoRequest.isActivo());

    
        model.setUsuarioCreacion(catalogoRequest.getUsuarioCreacion());

    
        if (catalogoRequest.getFechaCreacion() != null) {
            model.setFechaCreacion(catalogoRequest.getFechaCreacion().toString());
        } else {
            model.setFechaCreacion(null);
        }

    
        model.setUsuarioModificacion(catalogoRequest.getUsuarioModificacion());

        if (catalogoRequest.getFechaModificacion() != null) {
            model.setFechaModificacion(catalogoRequest.getFechaModificacion().toString());
        } else {
            model.setFechaModificacion(null);
        }


        CatalogoModel saved = catalogoRepository.save(model);

        return modelMapper.map(saved, CatalogoResponse.class);
        
    }

    @CacheEvict(value = "catalogos", allEntries = true)
    @Override
    public CatalogoResponse modificar(Integer id, CatalogoRequest catalogoRequest) {

         CatalogoModel model = catalogoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe una cuenta usuario con id: " + id));

   

        CatalogoModel actualizado = catalogoRepository.save(model);

        return modelMapper.map(actualizado, CatalogoResponse.class);


       
    }

    @Override
    public void eliminar(Integer id) {

        catalogoRepository.deleteById(id);
        
    }


    
}
