package unu.td.MsAcademico.config;
//
//import io.lettuce.core.protocol.ProtocolVersion;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//@Configuration
public class RedisConfig {
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration config =
//                new RedisStandaloneConfiguration("localhost", 6379);
//        config.setUsername("default");
//        config.setPassword("Unu#2025");
//
//        LettuceClientConfiguration clientConfig =
//                LettuceClientConfiguration.builder()
//                        .protocolVersion(ProtocolVersion.RESP2)
//                        .build();
//
//        return new LettuceConnectionFactory(config, clientConfig);
//    }
}
/*
# lechuga
spring.redis.client-type=lettuce
spring.redis.lettuce.shutdown-timeout=100ms
spring.redis.lettuce.pool.enabled=false
spring.redis.ssl.enabled=false
*/