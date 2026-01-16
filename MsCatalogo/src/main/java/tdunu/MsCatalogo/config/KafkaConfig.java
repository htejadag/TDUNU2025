package tdunu.MsCatalogo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    // Tópico para eventos de asistencia
    public static final String TOPIC_ASISTENCIA_REGISTRADA = "asistencia-registrada";
    public static final String TOPIC_CATALOGO_ACTUALIZADO = "catalogo-actualizado";

    @Bean
    public NewTopic asistenciaRegistradaTopic() {
        return TopicBuilder.name(TOPIC_ASISTENCIA_REGISTRADA)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic catalogoActualizadoTopic() {
        return TopicBuilder.name(TOPIC_CATALOGO_ACTUALIZADO)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
