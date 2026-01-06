package tdunu.MsSeguridad.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import tdunu.MsSeguridad.model.response.RoleResponse;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, List<RoleResponse>> redisTemplate(
            RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, List<RoleResponse>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(
                new Jackson2JsonRedisSerializer<>(List.class)
        );

        return template;
    }
}
