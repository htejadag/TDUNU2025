package com.unu.ms.MsSecretariaAcademica.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionService;

import com.fasterxml.jackson.databind.JsonNode;

@Configuration
class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
    
}

@Component
public class SecretariaAcademicaConsumerListener {

    @Autowired
    ResolucionService resolucionService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "consejo-secretaria-topic")

    public void onMessage(String mensaje) {

        try {
            JsonNode root = objectMapper.readTree(mensaje);

            JsonNode consejoNode = root.get("consejo");
            if (consejoNode != null) {
                int consejoId = consejoNode.asInt();
                System.out.println("ID Consejo: " + consejoId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
