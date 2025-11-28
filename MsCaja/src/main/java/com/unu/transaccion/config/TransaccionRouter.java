package com.unu.transaccion.config;

import com.unu.transaccion.handler.TransaccionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class TransaccionRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(TransaccionHandler handler) {
        return route()
                .path("/api/transacciones", builder -> builder
                        .GET("", handler::listar)
                        .GET("/{id}", handler::obtener)
                        .POST("", handler::crear)
                        .PUT("/{id}", handler::actualizar)
                        .DELETE("/{id}", handler::eliminar)
                )
                .build();
    }
}