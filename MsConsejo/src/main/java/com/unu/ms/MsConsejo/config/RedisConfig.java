package com.unu.ms.MsConsejo.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuración de Redis para caché distribuido.
 * 
 * Habilita el soporte de caché en Spring Boot utilizando Redis como almacén
 * distribuido. Configura la serialización JSON, tiempos de expiración (TTL)
 * y el template de Redis para operaciones directas sobre Redis.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * Configura el administrador de caché para Redis.
     * 
     * Establece la configuración por defecto de caché incluyendo:
     * - Serialización de claves en String
     * - Serialización de valores en JSON
     * - TTL (Time To Live) de 30 minutos para entradas en caché
     * - No cachear valores nulos
     * 
     * @param connectionFactory fábrica de conexiones a Redis
     * @return CacheManager configurado para Redis
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration config = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofMinutes(30));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

    /**
     * Configura el template de Redis para operaciones en caché.
     * 
     * Proporciona un template para ejecutar operaciones directas sobre Redis,
     * con serialización JSON para valores y String para claves, incluyendo
     * soporte para operaciones de hash.
     * 
     * @param connectionFactory fábrica de conexiones a Redis
     * @return RedisTemplate configurado con serialización JSON
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configurar serialización de claves como String
        template.setKeySerializer(new StringRedisSerializer());
        
        // Configurar serialización de valores como JSON
        template.setValueSerializer(RedisSerializer.json());
        
        // Configurar serialización para claves de hash como String
        template.setHashKeySerializer(new StringRedisSerializer());
        
        // Configurar serialización para valores de hash como JSON
        template.setHashValueSerializer(RedisSerializer.json());

        return template;
    }
}