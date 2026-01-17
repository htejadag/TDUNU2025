package tdunu.MsTitulacion.service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdunu.MsTitulacion.config.RedisConfig; // Importa tu nueva config
import tdunu.MsTitulacion.helper.NotificacionHelper;
import tdunu.MsTitulacion.model.entity.Dictamen;
import tdunu.MsTitulacion.model.request.DictamenRequest;
import tdunu.MsTitulacion.model.response.DictamenResponse;
import tdunu.MsTitulacion.repository.DictamenRepository;
import tdunu.MsTitulacion.service.DictamenService;

@Service
@Slf4j
public class DictamenServiceImp implements DictamenService {
    
    @Autowired
    private DictamenRepository dictamenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificacionHelper notificacionHelper;

    // USAMOS LAS CONSTANTES DE REDISCONFIG PARA EVITAR ERRORES DE TIPEO

    @Override
    // Ya no especificamos cacheManager, Spring usa el de RedisConfig automáticamente
    @Cacheable(value = RedisConfig.CACHE_DICTAMEN_LIST) 
    public List<DictamenResponse> listar(){
        return dictamenRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model, DictamenResponse.class))
        .collect(Collectors.toList());
    }

    @Override
    // Si esta categoría es muy consultada, agrégala al Map en RedisConfig.
    // Si no, usará la configuración por defecto (10 min).
    @Cacheable(value = "dictamen_categoria", key = "#categoria") 
    public List<DictamenResponse> listarByResultadoCat(String categoria){
        return dictamenRepository.findByResultadoCategoria(categoria)
        .stream()
        .map(model -> modelMapper.map(model, DictamenResponse.class))
        .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = RedisConfig.CACHE_DICTAMEN_ID, key = "#id", unless = "#result == null")
    public DictamenResponse obtenerPorId(int id){
        return dictamenRepository.findById(id)
        .map(model -> modelMapper.map(model, DictamenResponse.class))
        .orElse(null);
    }

    @Override
    // ALERTA: Aquí debes borrar TODAS las listas que puedan contener este dato
    @CacheEvict(value = {RedisConfig.CACHE_DICTAMEN_LIST, "dictamen_categoria"}, allEntries = true)
    public DictamenResponse guardar(DictamenRequest request){
        Dictamen model = modelMapper.map(request, Dictamen.class);
        
        // Forzamos ID 0 para asegurar INSERT
        model.setIdDictamen(0); 
        
        Dictamen saved = dictamenRepository.save(model);
        
        // Notificación
        if (saved.getFechaHora() != null) {
            notificacionHelper.notificarProgramacionSustentacion(saved);
        }
        
        return modelMapper.map(saved, DictamenResponse.class);
    }

    @Override
    // ALERTA: Al actualizar, borramos las listas Y el registro individual (ID) para que se refresque
    @CacheEvict(value = {
        RedisConfig.CACHE_DICTAMEN_LIST, 
        "dictamen_categoria", 
        RedisConfig.CACHE_DICTAMEN_ID
    }, allEntries = true)
    public DictamenResponse actualizar(int id, DictamenRequest request){
        Dictamen modelActual = dictamenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dictamen no encontrado"));

        // Protección contra nulos (VITAL para updates parciales)
        if(request.getAulaLugar() != null) modelActual.setAulaLugar(request.getAulaLugar());
        if(request.getFechaHora() != null) modelActual.setFechaHora(request.getFechaHora());
        if(request.getIdTesisBorrador() > 0) modelActual.setIdTesisBorrador(request.getIdTesisBorrador());
        if(request.getModalidadCategoria() != 0) modelActual.setModalidadCategoria(request.getModalidadCategoria());
        if(request.getNotaFinal() != null) modelActual.setNotaFinal(request.getNotaFinal());
        if(request.getResultadoCategoria() != 0) modelActual.setResultadoCategoria(request.getResultadoCategoria());

        Dictamen saved = dictamenRepository.save(modelActual);

        // Lógica de Notificación
        if (request.getNotaFinal() != null) {
            notificacionHelper.notificarResultadoFinal(saved);
        } else if (request.getFechaHora() != null) {
            // Solo notificamos reprogramación si cambió la fecha pero no hay nota final aún
            notificacionHelper.notificarProgramacionSustentacion(saved);
        }

        return modelMapper.map(saved, DictamenResponse.class);
    }

    @Override
    @CacheEvict(value = {
        RedisConfig.CACHE_DICTAMEN_LIST, 
        "dictamen_categoria", 
        RedisConfig.CACHE_DICTAMEN_ID
    }, allEntries = true)
    public void eliminar(int id) {
       dictamenRepository.deleteById(id);
    }
}