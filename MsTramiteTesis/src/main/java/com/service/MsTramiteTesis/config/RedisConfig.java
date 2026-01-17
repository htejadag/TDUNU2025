package com.service.MsTramiteTesis.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
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

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.activateDefaultTyping(
                                LaissezFaireSubTypeValidator.instance,
                                ObjectMapper.DefaultTyping.NON_FINAL,
                                JsonTypeInfo.As.PROPERTY);

                Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<Object>(
                                objectMapper, Object.class);

                template.setKeySerializer(new StringRedisSerializer());
                template.setHashKeySerializer(new StringRedisSerializer());

                template.setValueSerializer(jsonSerializer);
                template.setHashValueSerializer(jsonSerializer);

                return template;
        }

        @Bean
        @SuppressWarnings("null")
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.activateDefaultTyping(
                                LaissezFaireSubTypeValidator.instance,
                                ObjectMapper.DefaultTyping.NON_FINAL,
                                JsonTypeInfo.As.PROPERTY);

                Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<Object>(
                                objectMapper, Object.class);

                RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(10))
                                .serializeKeysWith(
                                                RedisSerializationContext.SerializationPair
                                                                .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(jsonSerializer))
                                .disableCachingNullValues();

                Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

                cacheConfigurations.put(CacheNames.CACHE_ESTUDIANTE_PROYECTOS,
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_ESTUDIANTE_PROYECTO,
                                defaultConfig.entryTtl(Duration.ofMinutes(10)));

                cacheConfigurations.put(CacheNames.CACHE_DOCENTE_ASESORIAS,
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_DOCENTE_JURADO, defaultConfig.entryTtl(Duration.ofMinutes(5)));
                cacheConfigurations.put(CacheNames.CACHE_DOCENTE_REVISIONES,
                                defaultConfig.entryTtl(Duration.ofMinutes(15)));

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
