package tdunu.MsTitulacion.service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdunu.MsTitulacion.model.entity.Dictamen;
import tdunu.MsTitulacion.model.request.DictamenRequest;
import tdunu.MsTitulacion.model.response.DictamenResponse;
import tdunu.MsTitulacion.repository.DictamenRepository;
import tdunu.MsTitulacion.service.DictamenService;

@Service
@Slf4j
public class DictamenServiceImp implements DictamenService{
    
    @Autowired
    private DictamenRepository dictamenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "dictamenlistar", cacheManager = "listCacheManager")
    public List<DictamenResponse> listar(){
        return dictamenRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model,DictamenResponse.class))
        .collect(Collectors.toList());
    }


    @Override
    @Cacheable(value = "dictamenCategoria", key="#categoria", cacheManager = "listCacheManager")
    public List<DictamenResponse> listarByResultadoCat(String categoria){
        return dictamenRepository.findByResultadoCategoria(categoria)
        .stream()
        .map(model -> modelMapper.map(model, DictamenResponse.class))
        .collect(Collectors.toList());
    }

    @Cacheable(value = "dictamenId", key="#id", cacheManager = "listCacheManager", unless = "#result == null")
    public DictamenResponse obtenerPorId(int id){
        return dictamenRepository.findById(id)
        .map(model -> modelMapper.map(model, DictamenResponse.class))
        .orElse(null);
    }

    @Override
    public DictamenResponse guardar(DictamenRequest request){
        Dictamen model = modelMapper.map(request,Dictamen.class);

        Dictamen saved = dictamenRepository.save(model);
        return modelMapper.map(saved, DictamenResponse.class);
    }

    @Override
    public DictamenResponse actualizar(int id,DictamenRequest request){
        Dictamen modelActual = dictamenRepository.findById(id).orElse(null);
        modelActual.setAulaLugar(request.getAulaLugar());
        modelActual.setFechaHora(request.getFechaHora());
        modelActual.setIdTesisBorrador(request.getIdTesisBorrador());
        modelActual.setModalidadCategoria(request.getModalidadCategoria());
        modelActual.setNotaFinal(request.getNotaFinal());
        modelActual.setResultadoCategoria(request.getResultadoCategoria());

        Dictamen saved = dictamenRepository.save(modelActual);
        return modelMapper.map(saved, DictamenResponse.class);
    }
    @Override
    public void eliminar (int id){
       dictamenRepository.deleteById(id);
    }
}
