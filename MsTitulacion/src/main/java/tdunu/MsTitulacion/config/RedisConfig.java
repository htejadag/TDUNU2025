package tdunu.MsTitulacion.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    @Primary
    public RedisCacheManager cacheManager(RedisConnectionFactory cf) {
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL)
                .build();

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Tiempo de vida del cache
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        return RedisCacheManager.builder(cf)
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public RedisCacheManager listCacheManager(RedisConnectionFactory cf) {

        Jackson2JsonRedisSerializer<Object> serializer =
                new Jackson2JsonRedisSerializer<>(Object.class);

        RedisCacheConfiguration config =RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        return RedisCacheManager.builder(cf)
                .cacheDefaults(config)
                .build();
    }
}
