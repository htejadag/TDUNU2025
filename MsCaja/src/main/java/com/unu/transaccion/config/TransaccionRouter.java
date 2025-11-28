package com.unu.transaccion.config;

import com.unu.transaccion.handler.TransaccionHandler;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class TransaccionRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(path = "/api/transacciones", method = RequestMethod.GET, beanClass = TransaccionHandler.class, beanMethod = "listar"),
            @RouterOperation(path = "/api/transacciones/{id}", method = RequestMethod.GET, beanClass = TransaccionHandler.class, beanMethod = "obtener"),
            @RouterOperation(path = "/api/transacciones", method = RequestMethod.POST, beanClass = TransaccionHandler.class, beanMethod = "crear"),
            @RouterOperation(path = "/api/transacciones/{id}", method = RequestMethod.PUT, beanClass = TransaccionHandler.class, beanMethod = "actualizar"),
            @RouterOperation(path = "/api/transacciones/{id}", method = RequestMethod.DELETE, beanClass = TransaccionHandler.class, beanMethod = "eliminar")
    })
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