package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Catalogo;
import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
import TDUNU2025.Msbiblioteca.repository.CatalogoRepository;
import TDUNU2025.Msbiblioteca.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j 
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ModelMapper modelMapper;

    private static final String CACHE_NAME = "catalogos";

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_NAME) 
    public List<CatalogoResponse> listarCatalogos() {
        log.info("Obteniendo listado de catálogos desde Base de Datos (Postgres)...");
        
        return catalogoRepository.findAll()
                .stream()
                .map(catalogo -> modelMapper.map(catalogo, CatalogoResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CACHE_NAME, key = "#id") 
    public CatalogoResponse obtenerCatalogoPorId(Integer id) {
        log.info("Buscando catálogo ID: {} en Postgres...", id);
        
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Catálogo no encontrado con ID: " + id));
        
        return modelMapper.map(catalogo, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true) 
    public CatalogoResponse guardarCatalogo(CatalogoRequest request) {
        validarDatosCatalogo(request);

        Catalogo catalogo = modelMapper.map(request, Catalogo.class);
        Catalogo guardado = catalogoRepository.save(catalogo);
        
        log.info("Catálogo guardado con éxito. ID: {}", guardado.getIdCatalogo());

        return modelMapper.map(guardado, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    public CatalogoResponse actualizarCatalogo(Integer id, CatalogoRequest request) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Catálogo no encontrado"));

        validarDatosCatalogo(request);

        modelMapper.map(request, catalogo);
        catalogo.setIdCatalogo(id); 

        Catalogo actualizado = catalogoRepository.save(catalogo);
        log.info("Catálogo ID: {} actualizado correctamente", id);

        return modelMapper.map(actualizado, CatalogoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    public void eliminarCatalogo(Integer id) {
        if (!catalogoRepository.existsById(id)) {
            throw new BusinessException("No se puede eliminar: Catálogo no encontrado");
        }
        catalogoRepository.deleteById(id);
        log.warn("Catálogo ID: {} eliminado", id);
    }

    private void validarDatosCatalogo(CatalogoRequest request) {
        if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre del catálogo es obligatorio");
        }
        if (request.getEstado() == null) {
             throw new BusinessException("El estado es obligatorio");
        }
        
        if (request.getEstado() != 1 && request.getEstado() != 0) {
            throw new BusinessException("El estado debe ser 1 (Activo) o 0 (Inactivo)");
        }
    }
}