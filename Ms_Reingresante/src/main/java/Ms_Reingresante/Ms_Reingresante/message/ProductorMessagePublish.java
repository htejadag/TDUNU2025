package Ms_Reingresante.Ms_Reingresante.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.SolicitudReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.ProcesoReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.ResolucionResponse;

@Component
public class ProductorMessagePublish {

    private final String topicName;
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ProductorMessagePublish(
            @Value("${spring.kafka.template.default-topic}") String topicName,
            KafkaTemplate<Integer, String> kafkaTemplate,
            ObjectMapper objectMapper) {

        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // Publica un evento Kafka cuando se guarda una Resolución
    public void sendResolucionEvent(ResolucionResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
    }

    // Publica un evento Kafka cuando se guarda un Proceso de Reingreso
    public void sendProcesoReingresoEvent(ProcesoReingresoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
    }

    // Publica un evento Kafka cuando se guarda un Informe Académico
    public void sendProcesoInformeAcademicoEvent(InformeAcademicoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
    }

    // Publica un evento Kafka cuando se guarda una Matrícula
    public void sendProcesoMatriculaEvent(MatriculaResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
    }

     // Publica un evento Kafka cuando se guarda una Ficha No Adeudo
    public void sendFichaNoAdeudoEvent(FichaNoAdeudoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
    }

    // Publica un evento Kafka cuando se guarda Solicitud de Reingresante
    public void sendSolicitudReingresoEvent(SolicitudReingresoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
    }
}




