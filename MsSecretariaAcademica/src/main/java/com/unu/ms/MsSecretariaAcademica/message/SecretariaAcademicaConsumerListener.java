package com.unu.ms.MsSecretariaAcademica.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionService;

 

@Component
public class SecretariaAcademicaConsumerListener {
    
    @Autowired
    ResolucionService resolucionService;

    @KafkaListener(topics = "consejo-secretaria-topic")
    public void onMessage(String consumerRecord) 
         {

      
        
        System.out.println("Mensaje recibido en Secretaria Academica: " + consumerRecord);
       
      

    }

}


