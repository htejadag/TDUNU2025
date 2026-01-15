package com.MsExamen.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic examenCreatedTopic() {
        return TopicBuilder.name("examen-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic examenUpdatedTopic() {
        return TopicBuilder.name("examen-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic examenDeletedTopic() {
        return TopicBuilder.name("examen-deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic preguntaCreatedTopic() {
        return TopicBuilder.name("pregunta-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic preguntaUpdatedTopic() {
        return TopicBuilder.name("pregunta-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic preguntaDeletedTopic() {
        return TopicBuilder.name("pregunta-deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic respuestaCreatedTopic() {
        return TopicBuilder.name("respuesta-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic respuestaUpdatedTopic() {
        return TopicBuilder.name("respuesta-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic respuestaDeletedTopic() {
        return TopicBuilder.name("respuesta-deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoCreatedTopic() {
        return TopicBuilder.name("catalogo-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoUpdatedTopic() {
        return TopicBuilder.name("catalogo-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoDeletedTopic() {
        return TopicBuilder.name("catalogo-deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoDetalleCreatedTopic() {
        return TopicBuilder.name("catalogo-detalle-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoDetalleUpdatedTopic() {
        return TopicBuilder.name("catalogo-detalle-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoDetalleDeletedTopic() {
        return TopicBuilder.name("catalogo-detalle-deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
