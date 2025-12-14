package com.MsExamen.service;

import com.MsExamen.exception.ResourceNotFoundException;

import com.MsExamen.dto.CatalogoDetalleDto;
import com.MsExamen.dto.request.CatalogoDetalleRequest;
import com.MsExamen.model.Catalogo;
import com.MsExamen.model.CatalogoDetalle;
import com.MsExamen.repository.CatalogoDetalleRepository;
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
public class CatalogoDetalleServiceImpl implements ICatalogoDetalleService {

    @Autowired
    private CatalogoDetalleRepository catalogoDetalleRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CatalogoDetalleDto> getAllCatalogoDetalles() {
        return catalogoDetalleRepository.findAll().stream()
                .map(detalle -> modelMapper.map(detalle, CatalogoDetalleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CatalogoDetalleDto getCatalogoDetalleById(Integer id) {
        CatalogoDetalle detalle = catalogoDetalleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("CatalogoDetalle not found with ID: {}", id);
                    return new ResourceNotFoundException(AppConstants.CATALOGO_DETALLE_NOT_FOUND);
                });
        return modelMapper.map(detalle, CatalogoDetalleDto.class);
    }

    @Override
    public CatalogoDetalleDto createCatalogoDetalle(CatalogoDetalleRequest request) {
        log.info("Creating new CatalogoDetalle with name: {}", request.getNombre());

        Catalogo catalogo = catalogoRepository.findById(request.getIdCatalogo())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATALOGO_NOT_FOUND));

        CatalogoDetalle detalle = modelMapper.map(request, CatalogoDetalle.class);
        detalle.setCatalogo(catalogo);

        if (detalle.getFechaCreacion() == null) {
            detalle.setFechaCreacion(LocalDateTime.now());
        }
        if (request.getUsuarioCreacion() != null) {
            detalle.setUsuarioCreacion(request.getUsuarioCreacion());
        }

        CatalogoDetalle savedDetalle = catalogoDetalleRepository.save(detalle);
        log.info("CatalogoDetalle created successfully with ID: {}", savedDetalle.getIdCatalogoDetalle());
        return modelMapper.map(savedDetalle, CatalogoDetalleDto.class);
    }

    @Override
    public CatalogoDetalleDto updateCatalogoDetalle(Integer id, CatalogoDetalleRequest request) {
        log.info("Updating CatalogoDetalle with ID: {}", id);
        CatalogoDetalle existingDetalle = catalogoDetalleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("CatalogoDetalle not found with ID: {}", id);
                    return new ResourceNotFoundException(AppConstants.CATALOGO_DETALLE_NOT_FOUND);
                });

        if (request.getIdCatalogo() != null) {
            Catalogo catalogo = catalogoRepository.findById(request.getIdCatalogo())
                    .orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATALOGO_NOT_FOUND));
            existingDetalle.setCatalogo(catalogo);
        }

        existingDetalle.setNombre(request.getNombre());
        existingDetalle.setValor(request.getValor());
        existingDetalle.setEstado(request.getEstado());

        if (request.getUsuarioModificacion() != null) {
            existingDetalle.setUsuarioModificacion(request.getUsuarioModificacion());
        }

        CatalogoDetalle updatedDetalle = catalogoDetalleRepository.save(existingDetalle);
        log.info("CatalogoDetalle updated successfully with ID: {}", id);
        return modelMapper.map(updatedDetalle, CatalogoDetalleDto.class);
    }

    @Override
    public void deleteCatalogoDetalle(Integer id) {
        if (!catalogoDetalleRepository.existsById(id)) {
            log.error("Cannot delete. CatalogoDetalle not found with ID: {}", id);
            throw new ResourceNotFoundException(AppConstants.CATALOGO_DETALLE_NOT_FOUND);
        }
        catalogoDetalleRepository.deleteById(id);
        log.info("CatalogoDetalle deleted successfully with ID: {}", id);
    }
}
