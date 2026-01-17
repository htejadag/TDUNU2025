package com.example.MsPlanEstudios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // Retorna el usuario actual. Por ahora retorna "SYSTEM" como default
        // Puede mejorarse integrando con SecurityContext para obtener usuario
        // autenticado
        return () -> Optional.of("SYSTEM");
    }
}
