package com.unu.transaccion.service;
import com.unu.transaccion.dto.TransaccionRequest;
import com.unu.transaccion.model.Transaccion;
import java.util.List;
import java.util.Optional;

public interface TransaccionService {
    List<Transaccion> listarTodas();
    Optional<Transaccion> obtenerPorId(Integer id);
    Transaccion guardar(TransaccionRequest request);
    Transaccion actualizar(Integer id, Transaccion transaccion);

    void eliminar(Integer id);
}
