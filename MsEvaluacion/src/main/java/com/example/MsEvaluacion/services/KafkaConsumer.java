package com.example.MsEvaluacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.CursoModel;
import com.example.MsEvaluacion.model.events.cursoDetalleEvent;
// import com.example.MsEvaluacion.model.request.EvaluacionRequest;
import com.example.MsEvaluacion.repository.cursoRepository;

// import com.example.MsEvaluacion.model.request.EvaluacionRequest;
// import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer {

    @Autowired
    private cursoRepository cursoRepo;
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    // private final IEvaluacionService evaluacionService;

    // public KafkaConsumer(IEvaluacionService evaluacionService) {
    //     this.evaluacionService = evaluacionService;
    // }

    @KafkaListener(topics = "curso-detalle-events" , containerFactory = "kafkaListenerContainerFactory")
    public void consume(cursoDetalleEvent message) {
        try {
            logger.info("Mensaje recibido de Kafka: {}", message);

            // Crear evaluación request con los datos de Kafka
            CursoModel request = new CursoModel();
            request.setIdDetalleCurso(message.getIdDetalleCurso());
            request.setCursoNombre(message.getCursoNombre());
            // request.setIdCurso(String.valueOf(message.getIdCurso()));
            // request.setFechaEvaluacion(LocalDateTime.now());

            // Guardar en BD usando método específico para Kafka
            cursoRepo.save(request);

            logger.info("Evaluación guardada exitosamente desde Kafka");

        } catch (Exception e) {
            logger.error("Error procesando mensaje de Kafka: {}", e.getMessage(), e);
        }
    }
}
