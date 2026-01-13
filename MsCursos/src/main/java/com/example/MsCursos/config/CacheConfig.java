package com.example.mscursos.config;

import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

        @Bean//convertidor de objetos a json
        public ObjectMapper redisObjectMapper() {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                return mapper;
        }

        @Bean//crud de cache que el spring usa
        public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
                        ObjectMapper redisObjectMapper) {

                var keySerializer = new StringRedisSerializer();//la clave en tipo texto
                var valueSerializer = new GenericJackson2JsonRedisSerializer(redisObjectMapper);//el valor en json

                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(10))//tiempo de vida del cache
                                .serializeKeysWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(keySerializer))//configuracion de serializacion de claves
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(valueSerializer));//configuracion de serializacion de valores

                return RedisCacheManager.builder(redisConnectionFactory)
                                .cacheDefaults(config)
                                .build();//crea el administrador de cache con la configuracion dada
        }
}
