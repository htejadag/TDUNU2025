package TDUNU2025.Msbiblioteca.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    private static final String TOPIC_PRESTAMOS = "biblioteca.prestamos.eventos";
    private static final String TOPIC_USUARIOS = "biblioteca.usuarios.actualizaciones";
    private static final int PARTITIONS_COUNT = 3;
    private static final int REPLICAS_COUNT = 1;

    @Bean
    public NewTopic topicPrestamos() {
        return TopicBuilder.name(TOPIC_PRESTAMOS)
                .partitions(PARTITIONS_COUNT)
                .replicas(REPLICAS_COUNT)
                .build();
    }

    @Bean
    public NewTopic topicUsuarios() {
        return TopicBuilder.name(TOPIC_USUARIOS)
                .partitions(PARTITIONS_COUNT)
                .replicas(REPLICAS_COUNT)
                .build();
    }
}