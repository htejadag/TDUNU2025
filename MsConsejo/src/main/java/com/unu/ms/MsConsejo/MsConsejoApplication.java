package com.unu.ms.MsConsejo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Aplicación principal del Microservicio de Consejo (MsConsejo).
 * 
 * Esta es la clase de punto de entrada para la aplicación Spring Boot.
 * Configura y habilita el caché para mejorar el rendimiento de las operaciones.
 * 
 * Funcionalidades principales:
 * - Gestión de consejos y sus miembros
 * - Gestión de sesiones del consejo
 * - Registro de asistencia
 * - Gestión de catálogos dinámicos
 * - Auditoría de operaciones
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@SpringBootApplication
@EnableCaching
public class MsConsejoApplication {

	/**
	 * Método principal para iniciar la aplicación Spring Boot.
	 * 
	 * @param args argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsConsejoApplication.class, args);
	}

}
