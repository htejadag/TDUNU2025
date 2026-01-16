package com.unu.ms.MsConsejo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuración de Jackson para serialización con soporte de tipos de fecha/hora.
 * 
 * Proporciona un ObjectMapper configurado para manejar correctamente
 * tipos de fecha y hora de Java 8 (LocalDate, LocalDateTime, etc.)
 * en la serialización JSON.
 */
@Configuration
class JacksonConfig {

    /**
     * Crea un ObjectMapper configurado con soporte de tipos de fecha/hora.
     * 
     * @return ObjectMapper configurado para serialización JSON con tipos temporales
     */
    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
    
}

/**
 * Publicador de eventos a Kafka para el microservicio de Consejo.
 * 
 * Componente responsable de enviar mensajes y eventos a Kafka para la
 * comunicación asíncrona con otros microservicios. Proporciona métodos
 * para enviar mensajes de texto y objetos complejos serializados a JSON.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Component
public class KafkaProducerPublisher {

    /** Nombre del topic de Kafka donde se enviarán los mensajes */
    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    /** Template de Kafka para enviar mensajes */
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    /** ObjectMapper para serialización JSON */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Envía un mensaje de texto simple al topic de Kafka configurado.
     * 
     * Utilizado para enviar mensajes de prueba o notificaciones simples
     * a otros microservicios a través de Kafka.
     * 
     * @param mensaje contenido del mensaje a enviar
     */
    public void sendMessage(String mensaje) {
        
        log.info("Inicio: enviar mensaje a Kafka");
        log.debug("Topic: {}, Mensaje: {}", topicName, mensaje);
        
        System.out.println("Enviando mensaje al topic de Kafka: " + topicName);
        kafkaTemplate.send(topicName, mensaje);
        
        log.info("Fin: mensaje enviado correctamente a Kafka");
    }

    /**
     * Envía un objeto modelo serializado a JSON al topic de Kafka.
     * 
     * Utilizado para enviar eventos complejos con información de sesiones
     * de consejo u otras entidades. El objeto se serializa automáticamente
     * a JSON incluyendo soporte para tipos de fecha/hora.
     * 
     * @param model objeto a serializar y enviar (típicamente SesionConsejoRequest)
     * @throws RuntimeException si ocurre un error durante la serialización JSON
     */
    public void sendSesionConsejoModel(Object model) {
        
        log.info("Inicio: enviar modelo SesionConsejo a Kafka");
        
        try {
            String json = objectMapper.writeValueAsString(model);

            log.debug("Topic: {}, Datos: {}", topicName, json);
            System.out.println("Enviando mensaje al topic de Kafka: " + json);
            kafkaTemplate.send(topicName, json);
            
            log.info("Fin: modelo SesionConsejo enviado correctamente a Kafka");
        } catch (JsonProcessingException e) {
            log.error("Error serializando mensaje Kafka", e);
            throw new RuntimeException("Error serializando mensaje Kafka", e);
        }
    }
}
