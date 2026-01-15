package com.MsExamen.service;

import com.MsExamen.exception.ResourceNotFoundException;
import com.MsExamen.dto.RespuestaDTO;
import com.MsExamen.dto.request.RespuestaRequest;
import com.MsExamen.model.Pregunta;
import com.MsExamen.model.Respuesta;
import com.MsExamen.repository.PreguntaRepository;
import com.MsExamen.repository.RespuestaRepository;
import com.MsExamen.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RespuestaServiceImpl implements IRespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public List<RespuestaDTO> getAllRespuestas() {
        return respuestaRepository.findAll().stream()
                .map(respuesta -> modelMapper.map(respuesta, RespuestaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RespuestaDTO> getRespuestasByPreguntaId(Integer idPregunta) {
        return respuestaRepository.findByPregunta_IdPregunta(idPregunta).stream()
                .map(respuesta -> modelMapper.map(respuesta, RespuestaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RespuestaDTO getRespuestaById(Integer id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.RESPUESTA_NOT_FOUND));
        return modelMapper.map(respuesta, RespuestaDTO.class);
    }

    @Override
    public RespuestaDTO createRespuesta(RespuestaRequest respuestaRequest) {
        log.info("Creating new Respuesta for Pregunta ID: {}", respuestaRequest.getIdPregunta());
        Respuesta respuesta = modelMapper.map(respuestaRequest, Respuesta.class);

        Pregunta pregunta = preguntaRepository.findById(respuestaRequest.getIdPregunta())
                .orElseThrow(() -> {
                    log.error("Pregunta not found with ID: {}", respuestaRequest.getIdPregunta());
                    return new ResourceNotFoundException(AppConstants.PREGUNTA_NOT_FOUND);
                });
        respuesta.setPregunta(pregunta);

        if (respuestaRequest.getUsuarioCreacion() != null) {
            respuesta.setUsuarioCreacion(respuestaRequest.getUsuarioCreacion());
        }

        Respuesta savedRespuesta = respuestaRepository.save(respuesta);
        log.info("Respuesta created successfully with ID: {}", savedRespuesta.getIdRespuesta());

        RespuestaDTO savedRespuestaDTO = modelMapper.map(savedRespuesta, RespuestaDTO.class);
        kafkaProducerService.sendEvent("respuesta-created", "RESPUESTA_CREATED", savedRespuestaDTO);

        return savedRespuestaDTO;
    }

    @Override
    public RespuestaDTO updateRespuesta(Integer id, RespuestaRequest respuestaRequest) {
        log.info("Updating Respuesta with ID: {}", id);
        Respuesta existingRespuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Respuesta not found with ID: {}", id);
                    return new ResourceNotFoundException(AppConstants.RESPUESTA_NOT_FOUND);
                });

        existingRespuesta.setRespuestaTexto(respuestaRequest.getRespuestaTexto());

        if (respuestaRequest.getUsuarioModificacion() != null) {
            existingRespuesta.setUsuarioModificacion(respuestaRequest.getUsuarioModificacion());
        }

        if (respuestaRequest.getIdPregunta() != null) {
            Pregunta pregunta = preguntaRepository.findById(respuestaRequest.getIdPregunta())
                    .orElseThrow(() -> {
                        log.error("Pregunta not found with ID: {}", respuestaRequest.getIdPregunta());
                        return new ResourceNotFoundException(AppConstants.PREGUNTA_NOT_FOUND);
                    });
            existingRespuesta.setPregunta(pregunta);
        }

        Respuesta updatedRespuesta = respuestaRepository.save(existingRespuesta);
        log.info("Respuesta updated successfully with ID: {}", id);

        RespuestaDTO updatedRespuestaDTO = modelMapper.map(updatedRespuesta, RespuestaDTO.class);
        kafkaProducerService.sendEvent("respuesta-updated", "RESPUESTA_UPDATED", updatedRespuestaDTO);

        return updatedRespuestaDTO;
    }

    @Override
    public void deleteRespuesta(Integer id) {
        if (!respuestaRepository.existsById(id)) {
            log.error("Cannot delete. Respuesta not found with ID: {}", id);
            throw new ResourceNotFoundException(AppConstants.RESPUESTA_NOT_FOUND);
        }
        respuestaRepository.deleteById(id);
        log.info("Respuesta deleted successfully with ID: {}", id);
        kafkaProducerService.sendEvent("respuesta-deleted", "RESPUESTA_DELETED", id);
    }
}
