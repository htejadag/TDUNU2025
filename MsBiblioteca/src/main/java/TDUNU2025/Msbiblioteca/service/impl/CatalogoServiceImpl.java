package tdunu2025.msbiblioteca.service.impl; // 1. Paquete corregido a minúsculas

import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.Catalogo;
import tdunu2025.msbiblioteca.model.request.CatalogoRequest;
import tdunu2025.msbiblioteca.model.response.CatalogoResponse;
import tdunu2025.msbiblioteca.repository.CatalogoRepository;
import tdunu2025.msbiblioteca.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "lista_catalogos") 
    public List<CatalogoResponse> listar() {
        try { 
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            // SOLUCIÓN A java:S2142: Restaurar el estado de interrupción
            Thread.currentThread().interrupt(); 
        } 

        List<Catalogo> catalogos = catalogoRepository.findAll();
        return catalogos.stream()
                .map(c -> modelMapper.map(c, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "catalogo", key = "#id") 
    public CatalogoResponse obtener(Long id) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogo", "id", id));
        return modelMapper.map(catalogo, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = "lista_catalogos", allEntries = true) 
    public CatalogoResponse registrar(CatalogoRequest request) {
        Catalogo catalogo = modelMapper.map(request, Catalogo.class);
        if(catalogo.getEstado() == null) catalogo.setEstado(1);
        
        Catalogo saved = catalogoRepository.save(catalogo);
        return modelMapper.map(saved, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"lista_catalogos", "catalogo"}, allEntries = true) 
    public CatalogoResponse actualizar(Long id, CatalogoRequest request) {
        Catalogo catalogoExistente = catalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogo", "id", id));

        modelMapper.map(request, catalogoExistente);
        catalogoExistente.setIdCatalogo(id);

        Catalogo updated = catalogoRepository.save(catalogoExistente);
        return modelMapper.map(updated, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"lista_catalogos", "catalogo"}, allEntries = true)
    public void eliminar(Long id) {
        if (!catalogoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        }
        catalogoRepository.deleteById(id);
    }
}