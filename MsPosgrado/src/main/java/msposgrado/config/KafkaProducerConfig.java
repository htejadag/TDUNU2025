package msposgrado.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuración del productor de Kafka para el sistema.
 *
 * <p>
 * Esta clase define los tópicos necesarios para la comunicación
 * basada en eventos dentro de la aplicación.
 * </p>
 *
 * <p>
 * Los tópicos se crean automáticamente al iniciar la aplicación
 * si no existen previamente en el broker de Kafka.
 * </p>
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * Crea el tópico de Kafka para eventos relacionados con solicitudes.
     *
     * <p>
     * Este tópico es utilizado para publicar eventos cuando se
     * realizan acciones sobre solicitudes, como creación,
     * actualización o cambios de estado.
     * </p>
     *
     * <p>
     * Configuración del tópico:
     * <ul>
     *   <li>Nombre: solicitudes-events</li>
     *   <li>Particiones: 1</li>
     *   <li>Réplicas: 1</li>
     * </ul>
     * </p>
     *
     * @return instancia del tópico {@link NewTopic}
     */
    @Bean
    public NewTopic solicitudesTopic() {
        return TopicBuilder.name("solicitudes-events")
                .partitions(1)
                .replicas(1)
                .build();
    }
}