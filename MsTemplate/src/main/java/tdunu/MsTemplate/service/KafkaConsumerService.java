package tdunu.MsTemplate.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "usuarios")
    public void consumirEvento(String mensaje) {
        System.out.println(
                "Mensaje recibido en " + this.getClass().getSimpleName() + ": " + mensaje
        );
    }
}
