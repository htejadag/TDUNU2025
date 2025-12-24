package com.microservice.MsCatalogoTesis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Configuración de MongoDB
 * Habilita el auditing automático para createdAt y updatedAt
 */
@Configuration
@EnableMongoAuditing
public class MongoConfig {
}
