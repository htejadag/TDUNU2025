package tdunu.MsTitulacion.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    // Constantes para los nombres de tus caché (Evita errores de tipeo en los @Cacheable)
    public static final String CACHE_DICTAMEN_LIST = "dictamen_list";
    public static final String CACHE_DICTAMEN_ID = "dictamen_id";
    public static final String CACHE_BORRADOR_LIST = "borrador_list";
    public static final String CACHE_BORRADOR_ID = "borrador_id";
    public static final String CACHE_RESOLUCION_LIST = "resolucion_list";

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        
        // 1. Configurar ObjectMapper para fechas y tipos de datos
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Vital para LocalDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Para guardar fechas como ISO String ("2026-01-...")
        
        // Esto permite que Redis sepa qué clase Java es cuando recupera los datos (evita ClassCastException)
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        // 2. Crear el Serializador JSON con el Mapper configurado
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // 3. Configuración por Defecto (Para cualquier caché que no especifiquemos abajo)
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Por defecto dura 10 mins
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        // 4. Configuraciones Específicas (Map)
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // -- LISTAS (Deben expirar rápido porque cambian mucho) --
        cacheConfigurations.put(CACHE_DICTAMEN_LIST, defaultConfig.entryTtl(Duration.ofMinutes(2)));
        cacheConfigurations.put(CACHE_BORRADOR_LIST, defaultConfig.entryTtl(Duration.ofMinutes(2)));
        cacheConfigurations.put(CACHE_RESOLUCION_LIST, defaultConfig.entryTtl(Duration.ofMinutes(5)));

        // -- REGISTROS INDIVIDUALES (Pueden durar más) --
        cacheConfigurations.put(CACHE_DICTAMEN_ID, defaultConfig.entryTtl(Duration.ofMinutes(20)));
        cacheConfigurations.put(CACHE_BORRADOR_ID, defaultConfig.entryTtl(Duration.ofMinutes(15)));

        // 5. Construir el Manager
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}