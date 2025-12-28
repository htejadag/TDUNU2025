package Ms_Reingresante.Ms_Reingresante.message.Consumidor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Ms_Reingresante.Ms_Reingresante.model.entity.MatriculaModel;
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.service.imp.MatriculaServiceImp;


@Component
public class GeneralConsumerListener {
    


    private Logger log = LoggerFactory.getLogger(GeneralConsumerListener.class);

    @Autowired
    MatriculaServiceImp service;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void OnMessage(ConsumerRecord<Integer, String> consumerRecord)
            throws JsonMappingException, JsonProcessingException {
        log.info("****************************************************************");
        log.info("ConsumerRecord : {}", consumerRecord.value());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = consumerRecord.value();
        MatriculaRequest data = objectMapper.readValue(jsonMessage, MatriculaRequest.class);

        // Logica del negocio
        MatriculaModel model = service.findById(data.getIdProceso());
        if (model.getIdResolucion() == data.getIdResolucion()) {
            model.setIdMatricula(2);
        }
        service.update(model);

    }



}
