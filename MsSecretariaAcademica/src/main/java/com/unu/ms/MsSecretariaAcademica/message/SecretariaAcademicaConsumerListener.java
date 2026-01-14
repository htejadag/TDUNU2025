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
            /*
            
            {
                "consejo":  1,
                "numeroSesion": "string",
                "nombreSesion": "string",
                "fechaSesion":[2026,1,14],
                "idTipoSesion": 0,
                "descripcion":  "string",
                "idEstado": 0,
                "usuarioRegistro":  0
            }
            
            */

            // Se extrae el ID del consejo del JSON recibido
            JsonNode consejoNode = root.get("consejo");
            if (consejoNode != null) {
                int consejoId = consejoNode.asInt();
                //SE MUESTRA EL ID DEL CONSEJO RECIBIDO
                System.out.println("ID Consejo: " + consejoId);
                //IMPLEMENTAR LA LÓGICA PARA ACTUALIZAR LAS RESOLUCIONES
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
