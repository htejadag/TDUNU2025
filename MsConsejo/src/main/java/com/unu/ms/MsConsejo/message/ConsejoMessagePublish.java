package com.unu.ms.MsConsejo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unu.ms.MsConsejo.model.entity.ConsejoModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsejoMessagePublish {
/* 
    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendDepositEvent(ConsejoModel consejoModel) throws JsonProcessingException {

        String value = objectMapper.writeValueAsString(consejoModel);
        kafkaTemplate.send(topicName, value);
    }
        */
}
