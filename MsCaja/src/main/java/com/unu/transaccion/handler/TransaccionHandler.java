package com.unu.transaccion.handler;

import com.unu.transaccion.dto.TransaccionRequest;
import com.unu.transaccion.model.Transaccion;
import com.unu.transaccion.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class TransaccionHandler {

    private final TransaccionService service;

    public ServerResponse listar(ServerRequest request) {
        return ServerResponse.ok().body(service.listarTodas());
    }

    public ServerResponse obtener(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return service.obtenerPorId(id)
                .map(t -> ServerResponse.ok().body(t))
                .orElse(ServerResponse.notFound().build());
    }

    public ServerResponse crear(ServerRequest request) throws Exception {
        // Extraemos el DTO del body
        TransaccionRequest transaccionRequest = request.body(TransaccionRequest.class);
        Transaccion creada = service.guardar(transaccionRequest);
        return ServerResponse.created(URI.create("/api/transacciones/" + creada.getIdTransaccion())).body(creada);
    }

    public ServerResponse actualizar(ServerRequest request) throws Exception {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        TransaccionRequest transaccionRequest = request.body(TransaccionRequest.class);
        try {
            Transaccion actualizada = service.actualizar(id, transaccionRequest);
            return ServerResponse.ok().body(actualizada);
        } catch (RuntimeException e) {
            return ServerResponse.notFound().build();
        }
    }

    public ServerResponse eliminar(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        service.eliminar(id);
        return ServerResponse.noContent().build();
    }
}
