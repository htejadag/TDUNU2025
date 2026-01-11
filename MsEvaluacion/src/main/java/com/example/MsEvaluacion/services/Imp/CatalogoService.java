package com.example.MsEvaluacion.services.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.CatalogoModel;
import com.example.MsEvaluacion.model.response.CatalogoResponse;
import com.example.MsEvaluacion.repository.ICatalogoRepository;
import com.example.MsEvaluacion.services.ICatalogoService;

@Service
public class CatalogoService implements ICatalogoService {

    @Autowired
    private ICatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper; 

    @Override
    @Cacheable(value = "lista_catalogos", key = "'all'")
    public List<CatalogoResponse> listarCatalogos() {
        System.out.println("--> CONSULTANDO A MONGODB (Listar Todos con Mapper) <--");
        
        List<CatalogoModel> listaEntidades = catalogoRepository.findAll();

        return listaEntidades.stream()
                .map(entidad -> modelMapper.map(entidad, CatalogoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "catalogos", key = "#codigo", unless = "#result == null")
    public CatalogoResponse obtenerCatalogoPorCodigo(String codigo) {
        System.out.println("Buscando en BD código: " + codigo);

        CatalogoModel entidad = catalogoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Catálogo no encontrado: " + codigo));

        return modelMapper.map(entidad, CatalogoResponse.class);
    }

    @Override
    @CacheEvict(value = {"catalogos", "lista_catalogos"}, allEntries = true) 
    public CatalogoResponse guardarCatalogo(CatalogoModel catalogo) {
        CatalogoModel entidadGuardada = catalogoRepository.save(catalogo);
        return modelMapper.map(entidadGuardada, CatalogoResponse.class);
    }
}