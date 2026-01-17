package com.MsSimulacro.MsSimulacro.controller;

import org.springframework.web.bind.annotation.*;

import com.MsSimulacro.MsSimulacro.kafka.EventoProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final EventoProducer producer;

    public KafkaTestController(EventoProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/enviar")
    public String enviar(@RequestParam String msg) {
        producer.enviar(msg);
        return "✅ Enviado a Kafka: " + msg;
    }
}
