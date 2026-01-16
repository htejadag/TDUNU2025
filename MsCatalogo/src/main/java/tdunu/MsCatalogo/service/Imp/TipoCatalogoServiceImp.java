package tdunu.MsCatalogo.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tdunu.MsCatalogo.model.entity.TipoCatalogoModel;
import tdunu.MsCatalogo.model.request.TipoCatalogoRequest;
import tdunu.MsCatalogo.model.response.TipoCatalogoResponse;
import tdunu.MsCatalogo.repository.TipoCatalogoRepository;
import tdunu.MsCatalogo.service.TipoCatalogoService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TipoCatalogoServiceImp implements TipoCatalogoService {

    @Autowired
    private TipoCatalogoRepository tipoCatalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "tipos", key = "'all'")
    public List<TipoCatalogoResponse> listar() {
        log.info("Consultando TODOS los tipos de catálogo desde BASE DE DATOS");
        return tipoCatalogoRepository.findByActivo(true)
                .stream()
                .map(model -> modelMapper.map(model, TipoCatalogoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(value = "tipos", key = "#id")
    public TipoCatalogoResponse obtenerPorId(Integer id) {
        log.info("Consultando tipo de catálogo ID: {} desde BASE DE DATOS", id);
        return tipoCatalogoRepository.findById(id)
                .map(model -> modelMapper.map(model, TipoCatalogoResponse.class))
                .orElse(null);
    }

    @Override
    @CacheEvict(value = "tipos", allEntries = true)
    public TipoCatalogoResponse guardar(TipoCatalogoRequest request) {
        log.info("Guardando nuevo tipo de catálogo: {}", request.getNombre());
        
        TipoCatalogoModel model = new TipoCatalogoModel();
        model.setCodigo(request.getCodigo());
        model.setNombre(request.getNombre());
        model.setDescripcion(request.getDescripcion());
        model.setActivo(request.getActivo() != null ? request.getActivo() : true);
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        
        TipoCatalogoModel saved = tipoCatalogoRepository.save(model);
        log.info("Cache de tipos LIMPIADO después de guardar");
        
        return modelMapper.map(saved, TipoCatalogoResponse.class);
    }

    @Override
    @CacheEvict(value = "tipos", allEntries = true)
    public TipoCatalogoResponse actualizar(Integer id, TipoCatalogoRequest request) {
        log.info("Actualizando tipo de catálogo ID: {}", id);
        
        return tipoCatalogoRepository.findById(id)
                .map(model -> {
                    model.setCodigo(request.getCodigo());
                    model.setNombre(request.getNombre());
                    model.setDescripcion(request.getDescripcion());
                    model.setActivo(request.getActivo());
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(request.getUsuarioCreacion());
                    
                    TipoCatalogoModel saved = tipoCatalogoRepository.save(model);
                    log.info("Cache de tipos LIMPIADO después de actualizar");
                    
                    return modelMapper.map(saved, TipoCatalogoResponse.class);
                })
                .orElse(null);
    }

    @Override
    @CacheEvict(value = "tipos", allEntries = true)
    public void eliminar(Integer id) {
        log.info("Eliminando tipo de catálogo ID: {}", id);
        tipoCatalogoRepository.deleteById(id);
        log.info("Cache de tipos LIMPIADO después de eliminar");
    }
}
