package tdunu.MsCatalogo.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import tdunu.MsCatalogo.kafka.KafkaProducerService;
import tdunu.MsCatalogo.model.entity.CatalogoModel;
import tdunu.MsCatalogo.model.request.CatalogoRequest;
import tdunu.MsCatalogo.model.response.CatalogoResponse;
import tdunu.MsCatalogo.repository.CatalogoRepository;
import tdunu.MsCatalogo.repository.TipoCatalogoRepository;
import tdunu.MsCatalogo.service.CatalogoService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CatalogoServiceImp implements CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private TipoCatalogoRepository tipoCatalogoRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "catalogos", key = "'all'")
    public List<CatalogoResponse> listar() {
        log.info("Consultando TODOS los catálogos desde BASE DE DATOS");
        return catalogoRepository.findByActivo(true)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Cacheable(value = "catalogos", key = "#id")
    public CatalogoResponse obtenerPorId(Integer id) {
        log.info("Consultando catálogo ID: {} desde BASE DE DATOS", id);
        return catalogoRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "catalogos", allEntries = true)
    })
    public CatalogoResponse guardar(CatalogoRequest request) {
        log.info("Guardando nuevo catálogo: {}", request.getNombre());
        
        CatalogoModel model = new CatalogoModel();
        model.setTipoCatalogoId(request.getTipoCatalogoId());
        model.setCodigo(request.getCodigo());
        model.setNombre(request.getNombre());
        model.setDescripcion(request.getDescripcion());
        model.setValor(request.getValor());
        model.setOrden(request.getOrden());
        model.setActivo(request.getActivo() != null ? request.getActivo() : true);
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        
        CatalogoModel saved = catalogoRepository.save(model);
        log.info("Cache de catálogos LIMPIADO después de guardar");
        
        // Enviar evento a Kafka
        kafkaProducerService.enviarCatalogoActualizado("CREADO", saved.getId(), saved.getNombre());
        
        return mapToResponse(saved);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "catalogos", allEntries = true)
    })
    public CatalogoResponse actualizar(Integer id, CatalogoRequest request) {
        log.info("Actualizando catálogo ID: {}", id);
        
        return catalogoRepository.findById(id)
                .map(model -> {
                    model.setTipoCatalogoId(request.getTipoCatalogoId());
                    model.setCodigo(request.getCodigo());
                    model.setNombre(request.getNombre());
                    model.setDescripcion(request.getDescripcion());
                    model.setValor(request.getValor());
                    model.setOrden(request.getOrden());
                    model.setActivo(request.getActivo());
                    model.setFechaModificacion(LocalDateTime.now());
                    model.setUsuarioModificacion(request.getUsuarioCreacion());
                    
                    CatalogoModel saved = catalogoRepository.save(model);
                    log.info("Cache de catálogos LIMPIADO después de actualizar");
                    
                    // Enviar evento a Kafka
                    kafkaProducerService.enviarCatalogoActualizado("ACTUALIZADO", saved.getId(), saved.getNombre());
                    
                    return mapToResponse(saved);
                })
                .orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "catalogos", allEntries = true)
    })
    public void eliminar(Integer id) {
        log.info("Eliminando catálogo ID: {}", id);
        catalogoRepository.deleteById(id);
        log.info("Cache de catálogos LIMPIADO después de eliminar");
        
        // Enviar evento a Kafka
        kafkaProducerService.enviarCatalogoActualizado("ELIMINADO", id, "");
    }

    @Override
    @Cacheable(value = "catalogos", key = "'tipo_' + #tipoCatalogoId")
    public List<CatalogoResponse> buscarPorTipo(Integer tipoCatalogoId) {
        log.info("Consultando catálogos por tipo: {} desde BASE DE DATOS", tipoCatalogoId);
        return catalogoRepository.findByTipoCatalogoIdAndActivo(tipoCatalogoId, true)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Cacheable(value = "catalogos", key = "'tipo_' + #tipoCatalogoId + '_codigo_' + #codigo")
    public CatalogoResponse buscarPorCodigo(Integer tipoCatalogoId, String codigo) {
        log.info("Consultando catálogo por tipo: {} y código: {} desde BASE DE DATOS", tipoCatalogoId, codigo);
        return catalogoRepository.findByTipoCatalogoIdAndCodigo(tipoCatalogoId, codigo)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    @CacheEvict(value = "catalogos", allEntries = true)
    public void limpiarCache() {
        log.info("LIMPIANDO todo el cache de catálogos manualmente");
    }

    private CatalogoResponse mapToResponse(CatalogoModel model) {
        CatalogoResponse response = modelMapper.map(model, CatalogoResponse.class);
        
        // Obtener nombre del tipo de catálogo
        tipoCatalogoRepository.findById(model.getTipoCatalogoId())
                .ifPresent(tipo -> response.setTipoCatalogoNombre(tipo.getNombre()));
        
        return response;
    }
}
