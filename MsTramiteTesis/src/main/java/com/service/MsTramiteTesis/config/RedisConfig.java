package com.service.MsTramiteTesis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
                RedisTemplate<String, Object> template = new RedisTemplate<>();
                template.setConnectionFactory(connectionFactory);

                // Serialize keys as Strings
                template.setKeySerializer(new StringRedisSerializer());

                // Serialize values as JSON
                template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

                // Hash key/value serialization
                template.setHashKeySerializer(new StringRedisSerializer());
                template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

                return template;
        }

        @Bean
        @SuppressWarnings("null")
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
                // Configuración por defecto: 10 minutos
                RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(10))
                                .serializeKeysWith(
                                                RedisSerializationContext.SerializationPair
                                                                .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                                .disableCachingNullValues();

                // Configuraciones personalizadas por cache
                Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

                // Estudiante - 5 minutos para listas, 10 para individual
                cacheConfigurations.put(CacheNames.CACHE_ESTUDIANTE_PROYECTOS,
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_ESTUDIANTE_PROYECTO,
                                defaultConfig.entryTtl(Duration.ofMinutes(10)));

                // Docente - 5 minutos para listas, 15 para revisiones
                cacheConfigurations.put(CacheNames.CACHE_DOCENTE_ASESORIAS,
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_DOCENTE_JURADO, defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_DOCENTE_REVISIONES,
                                defaultConfig.entryTtl(Duration.ofMinutes(15)));

                // Coordinador - 5 minutos para listas, 30 para jurados
                cacheConfigurations.put(CacheNames.CACHE_COORDINADOR_PENDIENTES,
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_COORDINADOR_TODOS,
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_COORDINADOR_PROYECTO,
                                defaultConfig.entryTtl(Duration.ofMinutes(10)));
                cacheConfigurations.put(CacheNames.CACHE_COORDINADOR_JURADOS,
                                defaultConfig.entryTtl(Duration.ofMinutes(30)));

                return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(defaultConfig)
                                .withInitialCacheConfigurations(cacheConfigurations)
                                .build();
        }
}
