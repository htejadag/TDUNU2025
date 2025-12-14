package com.MsExamen.service;

import com.MsExamen.dto.ExamenDTO;
import com.MsExamen.dto.request.ExamenRequest;
import com.MsExamen.model.Examen;
import com.MsExamen.model.CatalogoDetalle;
import com.MsExamen.repository.ExamenRepository;
import com.MsExamen.repository.CatalogoDetalleRepository;
import com.MsExamen.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ExamenServiceImpl implements IExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private CatalogoDetalleRepository catalogoDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ExamenDTO> getAllExamenes() {
        return examenRepository.findAll().stream()
                .map(examen -> modelMapper.map(examen, ExamenDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExamenDTO getExamenById(Integer id) {
        Examen examen = examenRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Examen not found with ID: {}", id);
                    return new RuntimeException(AppConstants.EXAMEN_NOT_FOUND);
                });
        return modelMapper.map(examen, ExamenDTO.class);
    }

    @Override
    public ExamenDTO createExamen(ExamenRequest examenRequest) {
        log.info("Creating new Examen with title: {}", examenRequest.getTitulo());

        Examen examen = new Examen();
        examen.setTitulo(examenRequest.getTitulo());
        examen.setDescripcion(examenRequest.getDescripcion());
        examen.setFechaCreacion(LocalDateTime.now());
        examen.setFechaModificacion(LocalDateTime.now());

        if (examenRequest.getIdTipoExamen() != null) {
            CatalogoDetalle tipoExamen = catalogoDetalleRepository.findById(examenRequest.getIdTipoExamen())
                    .orElseThrow(() -> new RuntimeException(AppConstants.CATALOGO_DETALLE_NOT_FOUND));
            examen.setTipoExamen(tipoExamen);
        }

        if (examenRequest.getUsuarioCreacion() != null) {
            examen.setUsuarioCreacion(examenRequest.getUsuarioCreacion());
        }

        Examen savedExamen = examenRepository.save(examen);
        log.info("Examen created successfully with ID: {}", savedExamen.getIdExamen());
        return modelMapper.map(savedExamen, ExamenDTO.class);
    }

    @Override
    public ExamenDTO updateExamen(Integer id, ExamenRequest examenRequest) {
        log.info("Updating Examen with ID: {}", id);
        Examen existingExamen = examenRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Examen not found with ID: {}", id);
                    return new RuntimeException(AppConstants.EXAMEN_NOT_FOUND);
                });

        existingExamen.setTitulo(examenRequest.getTitulo());
        existingExamen.setDescripcion(examenRequest.getDescripcion());

        if (examenRequest.getIdTipoExamen() != null) {
            CatalogoDetalle tipoExamen = catalogoDetalleRepository.findById(examenRequest.getIdTipoExamen())
                    .orElseThrow(() -> new RuntimeException(AppConstants.CATALOGO_DETALLE_NOT_FOUND));
            existingExamen.setTipoExamen(tipoExamen);
        }

        if (examenRequest.getUsuarioModificacion() != null) {
            existingExamen.setUsuarioModificacion(examenRequest.getUsuarioModificacion());
        }

        Examen updatedExamen = examenRepository.save(existingExamen);
        log.info("Examen updated successfully with ID: {}", id);
        return modelMapper.map(updatedExamen, ExamenDTO.class);
    }

    @Override
    public void deleteExamen(Integer id) {
        if (!examenRepository.existsById(id)) {
            log.error("Cannot delete. Examen not found with ID: {}", id);
            throw new RuntimeException(AppConstants.EXAMEN_NOT_FOUND);
        }
        examenRepository.deleteById(id);
        log.info("Examen deleted successfully with ID: {}", id);
    }
}
