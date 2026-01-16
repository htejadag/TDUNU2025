package com.unu.ms.MsConsejo.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Filtro HTTP para Mapped Diagnostic Context (MDC) del logging.
 * 
 * Intercepta todas las peticiones HTTP para agregar información de contexto
 * (método HTTP y URI) al MDC de SLF4J, permitiendo que los registros de log
 * incluyan automáticamente estos datos sin necesidad de pasarlos explícitamente.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Component
public class MDCFilter implements Filter {

    /**
     * Filtra las peticiones HTTP para agregar contexto al logging.
     * 
     * Extrae el método HTTP (GET, POST, PUT, DELETE, etc.) y la URI
     * de la petición y los coloca en el MDC para que estén disponibles
     * en todos los registros de log posteriores durante el procesamiento
     * de la petición.
     * 
     * @param request objeto ServletRequest de la petición
     * @param response objeto ServletResponse para la respuesta
     * @param chain FilterChain para continuar el procesamiento
     * @throws IOException si ocurre un error de entrada/salida
     * @throws ServletException si ocurre un error del servlet
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Agregar información de contexto al MDC
        MDC.put("method", req.getMethod());
        MDC.put("uri", req.getRequestURI());
        
        log.debug("MDC inicializado para petición - Método: {}, URI: {}", req.getMethod(), req.getRequestURI());

        try {
            // Continuar con el procesamiento de la petición
            chain.doFilter(request, response);
        } finally {
            // Limpiar el MDC al finalizar para evitar fugas de memoria
            log.debug("Limpiando MDC");
            MDC.clear();
        }
    }

}