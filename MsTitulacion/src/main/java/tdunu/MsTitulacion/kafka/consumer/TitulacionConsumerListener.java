package tdunu.MsTitulacion.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TitulacionConsumerListener {

    private static final Logger log = LoggerFactory.getLogger(TitulacionConsumerListener.class);

    private final ObjectMapper objectMapper;

    public TitulacionConsumerListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "titulacion-eventos", groupId = "ms-titulacion-group")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {

        log.info("****************************************************************");
        log.info("Mensaje Kafka => key={} value={}", consumerRecord.key(), consumerRecord.value());

        try {
            // Puedes procesar el evento aquí
            String eventoData = consumerRecord.value();
            
            // TODO: Implementar lógica de procesamiento del evento
            log.info("OK procesado => evento={}", consumerRecord.key());
        } catch (Exception e) {
            log.error("Error procesando mensaje Kafka: {}", consumerRecord.value(), e);
        }
    }
}
