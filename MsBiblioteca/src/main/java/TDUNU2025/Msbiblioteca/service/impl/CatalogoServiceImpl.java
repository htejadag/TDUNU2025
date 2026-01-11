package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.Catalogo;
import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
import TDUNU2025.Msbiblioteca.repository.CatalogoRepository;
import TDUNU2025.Msbiblioteca.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict; // Importante
import org.springframework.cache.annotation.Cacheable; // Importante
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ModelMapper modelMapper;

    // ⚡ REDIS: Si existe en caché "lista_catalogos", lo devuelve de ahí. Si no, va a BD.
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "lista_catalogos") 
    public List<CatalogoResponse> listar() {
        // Simulación de lentitud para que notes la diferencia (Quitar en producción)
        try { Thread.sleep(2000); } catch (InterruptedException e) {} 

        List<Catalogo> catalogos = catalogoRepository.findAll();
        return catalogos.stream()
                .map(c -> modelMapper.map(c, CatalogoResponse.class))
                .collect(Collectors.toList());
    }

    // ⚡ REDIS: Busca por ID especifico. La clave en Redis será "catalogo_1", "catalogo_2", etc.
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "catalogo", key = "#id") 
    public CatalogoResponse obtener(Long id) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogo", "id", id));
        return modelMapper.map(catalogo, CatalogoResponse.class);
    }

    // 🧹 LIMPIEZA: Al guardar nuevo, borramos la lista completa del caché para que se actualice.
    @Override
    @Transactional
    @CacheEvict(value = "lista_catalogos", allEntries = true) 
    public CatalogoResponse registrar(CatalogoRequest request) {
        Catalogo catalogo = modelMapper.map(request, Catalogo.class);
        if(catalogo.getEstado() == null) catalogo.setEstado(1);
        
        Catalogo saved = catalogoRepository.save(catalogo);
        return modelMapper.map(saved, CatalogoResponse.class);
    }

    // 🧹 LIMPIEZA: Al actualizar, borramos la lista Y el ítem específico del caché.
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

    // 🧹 LIMPIEZA: Al eliminar, limpiamos el caché.
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