package com.example.mscursos.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.example.mscursos.dto.EvaluacionFinalRegistradaEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        // ✅ pon tu bootstrap real (o pásalo por properties si prefieres)
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // ✅ pon tu group-id real
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "ms-cursos-group");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EvaluacionFinalRegistradaEvent> evaluacionFinalConsumerFactory() {

        JsonDeserializer<EvaluacionFinalRegistradaEvent> deserializer = new JsonDeserializer<>(
                EvaluacionFinalRegistradaEvent.class);

        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "ms-cursos-evaluacion-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean(name = "evaluacionFinalKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, EvaluacionFinalRegistradaEvent> evaluacionFinalKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, EvaluacionFinalRegistradaEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(evaluacionFinalConsumerFactory());
        return factory;
    }

}
