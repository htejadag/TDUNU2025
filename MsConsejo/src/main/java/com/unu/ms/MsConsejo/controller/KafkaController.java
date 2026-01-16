package com.unu.ms.MsConsejo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.message.KafkaProducerPublisher;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para pruebas de integración con Kafka.
 * 
 * Proporciona endpoints para enviar mensajes de prueba a Kafka y verificar
 * la conectividad con el sistema de mensajería distribuida. Utilizado
 * principalmente para testing y validación de la integración con Kafka.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@RestController
@RequestMapping(ApiRoutes.KafkaTest.BASE)
@Tag(name = "Kafka Test Controller")
public class KafkaController {

    /** Servicio productor de Kafka para enviar mensajes */
    @Autowired
    private KafkaProducerPublisher kafkaProducerService;

    /**
     * Envía un mensaje de prueba a Kafka.
     * 
     * Utilizado para verificar la conectividad y funcionamiento correcto
     * de la integración con Kafka. El mensaje se envía al topic configurado
     * en el productor de Kafka.
     * 
     * @param mensaje texto del mensaje a enviar a Kafka
     * @return respuesta base confirmando el envío del mensaje
     */
    @PostMapping(value = ApiRoutes.KafkaTest.ENVIAR_MENSAJE)
    public ResponseBase<String> enviarMensaje(@RequestBody String mensaje) {

        log.info("Inicio request: enviar mensaje a Kafka");
        log.debug("Mensaje a enviar: {}", mensaje);

        kafkaProducerService.sendMessage(mensaje);
        
        log.info("Fin request: mensaje enviado correctamente a Kafka");
        
        return ResponseBase.ok("Mensaje enviado correctamente al topic de Kafka.");

    }

}
