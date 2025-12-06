package com.MsExamen.service;

import com.MsExamen.dto.PreguntaDTO;
import com.MsExamen.dto.request.PreguntaRequest;
import com.MsExamen.model.Examen;
import com.MsExamen.model.Pregunta;
import com.MsExamen.repository.ExamenRepository;
import com.MsExamen.repository.PreguntaRepository;
import com.MsExamen.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PreguntaServiceImpl implements IPreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PreguntaDTO> getAllPreguntas() {
        return preguntaRepository.findAll().stream()
                .map(pregunta -> modelMapper.map(pregunta, PreguntaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PreguntaDTO> getPreguntasByExamenId(Integer idExamen) {
        return preguntaRepository.findByExamen_IdExamen(idExamen).stream()
                .map(pregunta -> modelMapper.map(pregunta, PreguntaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PreguntaDTO getPreguntaById(Integer id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(AppConstants.PREGUNTA_NOT_FOUND));
        return modelMapper.map(pregunta, PreguntaDTO.class);
    }

    @Override
    public PreguntaDTO createPregunta(PreguntaRequest preguntaRequest) {
        log.info("Creating new Pregunta for Examen ID: {}", preguntaRequest.getIdExamen());
        Pregunta pregunta = modelMapper.map(preguntaRequest, Pregunta.class);

        Examen examen = examenRepository.findById(preguntaRequest.getIdExamen())
                .orElseThrow(() -> {
                    log.error("Examen not found with ID: {}", preguntaRequest.getIdExamen());
                    return new RuntimeException(AppConstants.EXAMEN_NOT_FOUND);
                });
        pregunta.setExamen(examen);

        if (preguntaRequest.getUsuarioCreacion() != null) {
            pregunta.setUsuarioCreacion(preguntaRequest.getUsuarioCreacion());
        }

        Pregunta savedPregunta = preguntaRepository.save(pregunta);
        log.info("Pregunta created successfully with ID: {}", savedPregunta.getIdPregunta());
        return modelMapper.map(savedPregunta, PreguntaDTO.class);
    }

    @Override
    public PreguntaDTO updatePregunta(Integer id, PreguntaRequest preguntaRequest) {
        log.info("Updating Pregunta with ID: {}", id);
        Pregunta existingPregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pregunta not found with ID: {}", id);
                    return new RuntimeException(AppConstants.PREGUNTA_NOT_FOUND);
                });

        existingPregunta.setPreguntaTexto(preguntaRequest.getPreguntaTexto());
        existingPregunta.setTipo(preguntaRequest.getTipo());

        if (preguntaRequest.getUsuarioModificacion() != null) {
            existingPregunta.setUsuarioModificacion(preguntaRequest.getUsuarioModificacion());
        }

        if (preguntaRequest.getIdExamen() != null) {
            Examen examen = examenRepository.findById(preguntaRequest.getIdExamen())
                    .orElseThrow(() -> {
                        log.error("Examen not found with ID: {}", preguntaRequest.getIdExamen());
                        return new RuntimeException(AppConstants.EXAMEN_NOT_FOUND);
                    });
            existingPregunta.setExamen(examen);
        }

        Pregunta updatedPregunta = preguntaRepository.save(existingPregunta);
        log.info("Pregunta updated successfully with ID: {}", id);
        return modelMapper.map(updatedPregunta, PreguntaDTO.class);
    }

    @Override
    public void deletePregunta(Integer id) {
        if (!preguntaRepository.existsById(id)) {
            log.error("Cannot delete. Pregunta not found with ID: {}", id);
            throw new RuntimeException(AppConstants.PREGUNTA_NOT_FOUND);
        }
        preguntaRepository.deleteById(id);
        log.info("Pregunta deleted successfully with ID: {}", id);
    }
}
