package Ms_Reingresante.Ms_Reingresante.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    // Aquí va la declaración del redisTemplate SIN final
    private StringRedisTemplate redisTemplate;

    // Setter para inyección de redisTemplate
    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Constructor que inyecta las otras dependencias
    public ProductorMessagePublish(
            @Value("${spring.kafka.template.default-topic}") String topicName,
            KafkaTemplate<Integer, String> kafkaTemplate,
            ObjectMapper objectMapper) {

        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // Publica un evento Kafka cuando guarda una Resolucion y Redis
    public void sendResolucionEvent(ResolucionResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
        if (redisTemplate != null && response.getResNumero() != null) {
            redisTemplate.opsForValue().set("Resolucion:" + response.getResNumero(), value);
        }
    }

    // Publica un evento Kafka cuando se guarda un Proceso de Reingreso y redis
    public void sendProcesoReingresoEvent(ProcesoReingresoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
        if (redisTemplate != null && response.getProcCodigo() != null) {
            redisTemplate.opsForValue().set("Proceso de Reingreso:" + response.getProcCodigo(), value);
        }
    }

    // Publica un evento Kafka cuando se guarda un Informe Académico y Redis
    public void sendProcesoInformeAcademicoEvent(InformeAcademicoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
         if (redisTemplate != null && response.getInfNumero() != null) {
            redisTemplate.opsForValue().set("Informe Academico N-:" + response.getInfNumero(), value);
        }
    }

    // Publica un evento Kafka cuando se guarda una Matrícula y redis
    public void sendProcesoMatriculaEvent(MatriculaResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
         if (redisTemplate != null && response.getIdMatricula() != null) {
            redisTemplate.opsForValue().set("Matricula:" + response.getIdMatricula(), value);
        }
    }

     // Publica un evento Kafka cuando se guarda una Ficha No Adeudo y redis
    public void sendFichaNoAdeudoEvent(FichaNoAdeudoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
         if (redisTemplate != null && response.getFichaNumero() != null) {
            redisTemplate.opsForValue().set("Ficha no Adeudo:" + response.getFichaNumero(), value);
        }
    }

    // Publica un evento Kafka cuando se guarda Solicitud de Reingresante y redis
    public void sendSolicitudReingresoEvent(SolicitudReingresoResponse response) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(response);
        kafkaTemplate.send(topicName, value);
         if (redisTemplate != null && response.getSolNumRegistro() != null) {
            redisTemplate.opsForValue().set("Solicitud de Reingresante:" + response.getSolNumRegistro(), value);
        }
    }
}




