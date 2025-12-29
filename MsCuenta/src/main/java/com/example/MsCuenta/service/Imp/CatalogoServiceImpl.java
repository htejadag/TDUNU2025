package com.example.MsCuenta.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CatalogoModel;
import com.example.MsCuenta.model.request.Catalogo.CatalogoRequest;
import com.example.MsCuenta.model.request.Catalogo.CatalogoUpdateRequest;
import com.example.MsCuenta.model.response.CatalogoResponse;
import com.example.MsCuenta.repository.CatalogoRepository;
import com.example.MsCuenta.service.CatalogoCacheService;
import com.example.MsCuenta.service.CatalogoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CatalogoServiceImpl implements CatalogoService {

    
    @Autowired
    CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper; 

     @Autowired
    private CatalogoCacheService cacheService;


    @Override
    public List<CatalogoResponse> listar() {

         return cacheService.listarCacheado()
            .stream()
            .map(model -> modelMapper.map(model, CatalogoResponse.class))
            .toList();
       
    }

    @Override
    public CatalogoResponse obtenerPorId(Integer id) {

        return cacheService.listarCacheado()
        .stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .orElse(null);
        
    }

    @CacheEvict(value = "catalogos", allEntries = true)
    @Override
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest) {

        CatalogoModel model =modelMapper.map(catalogoRequest, CatalogoModel.class);

        model.setTipo(catalogoRequest.getTipo());
        model.setActivo(catalogoRequest.isActivo());
        model.setUsuarioCreacion(catalogoRequest.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());


        CatalogoModel saved = catalogoRepository.save(model);

        return modelMapper.map(saved, CatalogoResponse.class);

    
        
    }

    @CacheEvict(value = "catalogos", allEntries = true)
    @Override
    public CatalogoResponse modificar(Integer id, CatalogoUpdateRequest catalogoRequest) {

         CatalogoModel model = catalogoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un catalogocon id: " + id));

        model.setTipo(catalogoRequest.getTipo());
        model.setActivo(catalogoRequest.isActivo());
        model.setUsuarioModificacion(catalogoRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        CatalogoModel actualizado = catalogoRepository.save(model);

        return modelMapper.map(actualizado, CatalogoResponse.class);


       
    }

    @CacheEvict(value = "catalogos", allEntries = true)
    @Override
    public CatalogoResponse eliminar(Integer id) {

        CatalogoModel model = catalogoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un catalogocon id: " + id));

        model.setActivo(false);

        CatalogoModel actualizado = catalogoRepository.save(model);
    
        return modelMapper.map(actualizado, CatalogoResponse.class);
      
        
    }


    
}
