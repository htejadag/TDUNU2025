package com.microservice.MsCatalogoTesis.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
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
        public ObjectMapper redisObjectMapper() {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                                .allowIfBaseType(Object.class)
                                .build();

                mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

                return mapper;
        }

        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
                RedisTemplate<String, Object> template = new RedisTemplate<>();
                template.setConnectionFactory(connectionFactory);

                StringRedisSerializer stringSerializer = new StringRedisSerializer();
                Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(
                                redisObjectMapper(), Object.class);

                template.setKeySerializer(stringSerializer);
                template.setHashKeySerializer(stringSerializer);
                template.setValueSerializer(jsonSerializer);
                template.setHashValueSerializer(jsonSerializer);

                template.afterPropertiesSet();
                return template;
        }

        @Bean
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
                RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5))
                                .serializeKeysWith(
                                                RedisSerializationContext.SerializationPair
                                                                .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(
                                                RedisSerializationContext.SerializationPair
                                                                .fromSerializer(new Jackson2JsonRedisSerializer<>(
                                                                                redisObjectMapper(), Object.class)))
                                .disableCachingNullValues();

                Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

                cacheConfigurations.put("catalogo",
                                defaultConfig.entryTtl(Duration.ofMinutes(5)));

                cacheConfigurations.put("catalogoPorGrupo",
                                defaultConfig.entryTtl(Duration.ofMinutes(10)));

                cacheConfigurations.put("catalogoActivosPorGrupo",
                                defaultConfig.entryTtl(Duration.ofMinutes(10)));

                cacheConfigurations.put("catalogoElemento",
                                defaultConfig.entryTtl(Duration.ofMinutes(15)));

                return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(defaultConfig)
                                .withInitialCacheConfigurations(cacheConfigurations)
                                .transactionAware()
                                .build();
        }
}
