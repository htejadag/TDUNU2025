package com.example.mscursos.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareConfig {

    @Bean(name = "auditorAware")
    public AuditorAware<Integer> auditorAware() {
        return () -> Optional.of(1);
    }
}
