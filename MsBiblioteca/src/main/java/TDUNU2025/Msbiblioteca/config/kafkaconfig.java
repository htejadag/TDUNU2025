package TDUNU2025.Msbiblioteca.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topicPrestamos() {
        return TopicBuilder.name("biblioteca.prestamos.eventos")
                .partitions(3) 
                .replicas(1)   
                .build();
    }

    @Bean
    public NewTopic topicUsuarios() {
        return TopicBuilder.name("biblioteca.usuarios.actualizaciones")
                .partitions(3)
                .replicas(1)
                .build();
    }
}