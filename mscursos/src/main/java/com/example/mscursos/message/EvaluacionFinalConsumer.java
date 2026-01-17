package com.example.mscursos.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.mscursos.dto.EvaluacionFinalRegistradaEvent;
import com.example.mscursos.model.entity.CursoDetalleModel;
import com.example.mscursos.repository.CursoDetalleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EvaluacionFinalConsumer {

    private final CursoDetalleRepository cursoDetalleRepository;

    @KafkaListener(topics = "${app.kafka.topic.evaluacion-final-events}", groupId = "ms-cursos-evaluacion-group", containerFactory = "evaluacionFinalKafkaListenerContainerFactory")
    public void consumir(EvaluacionFinalRegistradaEvent event) {

        log.info("Evento EvaluacionFinal recibido correctamente: {}", event);

        if (!"CURSO_COMPLETADO".equals(event.getAction())) {
            return;
        }

        CursoDetalleModel cursoDetalle = cursoDetalleRepository
                .findById(event.getIdCursoDetalle())
                .orElse(null);

        if (cursoDetalle == null) {
            log.warn("CursoDetalle no encontrado: {}", event.getIdCursoDetalle());
            return;
        }

        cursoDetalle.setEstado(false);
        cursoDetalleRepository.save(cursoDetalle);

        log.info("CursoDetalle {} cerrado correctamente", cursoDetalle.getId());
    }
}
