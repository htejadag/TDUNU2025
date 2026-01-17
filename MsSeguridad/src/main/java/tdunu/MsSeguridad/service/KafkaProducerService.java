package tdunu.MsSeguridad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void enviarEventoUsuario(String mensaje) {
        kafkaTemplate.send("usuarios", mensaje);
        System.out.println("Mensaje enviado a Kafka: " + mensaje);
    }

}
