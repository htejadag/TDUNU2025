package com.MsExamen.service;

import com.MsExamen.dto.CatalogoDto;
import com.MsExamen.dto.request.CatalogoRequest;
import com.MsExamen.model.Catalogo;
import com.MsExamen.repository.CatalogoRepository;
import com.MsExamen.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CatalogoServiceImpl implements ICatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CatalogoDto> getAllCatalogos() {
        return catalogoRepository.findAll().stream()
                .map(catalogo -> modelMapper.map(catalogo, CatalogoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CatalogoDto getCatalogoById(Integer id) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Catalogo not found with ID: {}", id);
                    return new RuntimeException(AppConstants.CATALOGO_NOT_FOUND);
                });
        return modelMapper.map(catalogo, CatalogoDto.class);
    }

    @Override
    public CatalogoDto createCatalogo(CatalogoRequest catalogoRequest) {
        log.info("Creating new Catalogo with name: {}", catalogoRequest.getNombre());
        Catalogo catalogo = modelMapper.map(catalogoRequest, Catalogo.class);

        if (catalogo.getFechaCreacion() == null) {
            catalogo.setFechaCreacion(LocalDateTime.now());
        }
        if (catalogoRequest.getUsuarioCreacion() != null) {
            catalogo.setUsuarioCreacion(catalogoRequest.getUsuarioCreacion());
        }

        Catalogo savedCatalogo = catalogoRepository.save(catalogo);
        log.info("Catalogo created successfully with ID: {}", savedCatalogo.getIdCatalogo());
        return modelMapper.map(savedCatalogo, CatalogoDto.class);
    }

    @Override
    public CatalogoDto updateCatalogo(Integer id, CatalogoRequest catalogoRequest) {
        log.info("Updating Catalogo with ID: {}", id);
        Catalogo existingCatalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Catalogo not found with ID: {}", id);
                    return new RuntimeException(AppConstants.CATALOGO_NOT_FOUND);
                });

        existingCatalogo.setNombre(catalogoRequest.getNombre());
        existingCatalogo.setDescripcion(catalogoRequest.getDescripcion());
        existingCatalogo.setEstado(catalogoRequest.getEstado());

        if (catalogoRequest.getUsuarioModificacion() != null) {
            existingCatalogo.setUsuarioModificacion(catalogoRequest.getUsuarioModificacion());
        }

        Catalogo updatedCatalogo = catalogoRepository.save(existingCatalogo);
        log.info("Catalogo updated successfully with ID: {}", id);
        return modelMapper.map(updatedCatalogo, CatalogoDto.class);
    }

    @Override
    public void deleteCatalogo(Integer id) {
        if (!catalogoRepository.existsById(id)) {
            log.error("Cannot delete. Catalogo not found with ID: {}", id);
            throw new RuntimeException(AppConstants.CATALOGO_NOT_FOUND);
        }
        catalogoRepository.deleteById(id);
        log.info("Catalogo deleted successfully with ID: {}", id);
    }
}
