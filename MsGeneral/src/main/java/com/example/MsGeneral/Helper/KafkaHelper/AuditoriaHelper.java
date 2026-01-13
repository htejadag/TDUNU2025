package com.example.MsGeneral.Helper.KafkaHelper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.MsGeneral.Kafka.Event.AuditoriaEvent;
import com.example.MsGeneral.Kafka.Producer.AuditoriaProducer;
import com.example.MsGeneral.Model.Entidad.Catalogo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditoriaHelper {

    private final AuditoriaProducer auditoriaProducer;

    public void publicarEvento(Catalogo catalogo, String operacion) {
        try {
            AuditoriaEvent event = AuditoriaEvent.builder()
                    .microservicio("MsGeneral")
                    .modulo("Catalogo")
                    .operacion(operacion)
                    .entidad("Catalogo")
                    .idEntidad(catalogo.getIdCatalogo())
                    .datos(catalogo)
                    .usuario("System")
                    .fechaEvento(LocalDateTime.now())
                    .build();

            auditoriaProducer.publish(event);

        } catch (Exception e) {
            log.warn("No se pudo publicar evento Kafka (Catalogo - {})", operacion, e);
        }
    }

}
