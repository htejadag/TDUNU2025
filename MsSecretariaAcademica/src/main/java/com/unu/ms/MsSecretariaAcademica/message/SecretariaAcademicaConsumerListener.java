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

        System.out.println("Mensaje recibido en Secretaria Academica: " + mensaje);

        try {
            JsonNode root = objectMapper.readTree(mensaje);

            JsonNode consejoNode = root.get("consejo");
            if (consejoNode != null) {
                int consejoId = consejoNode.asInt();

                /**
                 * {
                 * "consejo":1,
                 * "numeroSesion":
                 * "strasdadsasding",
                 * "nombreSesion":"striasdasdasdasdng",
                 * "fechaSesion":[2026,1,15],
                 * "idTipoSesion":0,
                 * "descripcion":"striasdasdasdasdng",
                 * "idEstado":0,
                 * "usuarioRegistro":0
                 * }
                 */

                System.out.println("_____________________________________________________________________________");
                System.out.println("ID Consejo: " + consejoId);
                System.out.println("numeroSesion: " + root.get("numeroSesion").asText());
                System.out.println("nombreSesion: " + root.get("nombreSesion").asText());
                System.out.println("fechaSesion: " + root.get("fechaSesion").asText());
                System.out.println("idTipoSesion: " + root.get("idTipoSesion").asInt());
                System.out.println("descripcion: " + root.get("descripcion").asText());
                System.out.println("idEstado: " + root.get("idEstado").asInt());
                System.out.println("usuarioRegistro: " + root.get("usuarioRegistro").asInt());
                System.out.println("_____________________________________________________________________________");
                System.out.println("Procesando consejo en ResolucionService...");

                //resolucionService.procesarConsejo(consejoId);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
