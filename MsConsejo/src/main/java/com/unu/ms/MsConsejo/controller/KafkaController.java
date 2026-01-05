package com.unu.ms.MsConsejo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.message.KafkaProducerPublisher;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.KafkaTest.BASE)
public class KafkaController {

    @Autowired
    KafkaProducerPublisher kafkaProducerService;

    @PostMapping(value = ApiRoutes.KafkaTest.ENVIAR_MENSAJE)
    public ResponseBase<String> enviarMensaje(@RequestBody String mensaje) {

        kafkaProducerService.sendMessage(mensaje);
        return ResponseBase.ok("Mensaje enviado correctamente al topic de Kafka.");

    }

}
