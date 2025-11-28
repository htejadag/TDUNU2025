package com.unu.transaccion.service.impl;

import com.unu.transaccion.dto.TransaccionRequest;
import com.unu.transaccion.model.*;
import com.unu.transaccion.repository.*;
import com.unu.transaccion.service.TransaccionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    // Inyectamos los repositorios auxiliares
    private final CajeroRepository cajeroRepository;
    private final ClienteRepository clienteRepository;
    private final TipoOperacionRepository tipoOperacionRepository;
    private final TipoPagoRepository tipoPagoRepository;

    @Override
    public List<Transaccion> listarTodas() {
        return transaccionRepository.findAll();
    }

    @Override
    public Optional<Transaccion> obtenerPorId(Integer id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public Transaccion guardar(TransaccionRequest request) {
        Transaccion transaccion = new Transaccion();

        // Mapear datos simples
        transaccion.setFechaHora(LocalDateTime.now()); // Asignamos fecha actual automática
        transaccion.setCorrelativo(request.getCorrelativo());
        transaccion.setMontoTotal(request.getMontoTotal());
        transaccion.setDescuento(request.getDescuento());
        transaccion.setObservaciones(request.getObservaciones());

        // Buscar y asignar entidades relacionadas (lanzamos error si no existen)
        transaccion.setCajero(cajeroRepository.findById(request.getIdCajero())
                .orElseThrow(() -> new EntityNotFoundException("Cajero no encontrado")));

        transaccion.setCliente(clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));

        transaccion.setTipoOperacion(tipoOperacionRepository.findById(request.getIdTipoOperacion())
                .orElseThrow(() -> new EntityNotFoundException("Tipo Operación no encontrado")));

        transaccion.setTipoPago(tipoPagoRepository.findById(request.getIdTipoPago())
                .orElseThrow(() -> new EntityNotFoundException("Tipo Pago no encontrado")));

        return transaccionRepository.save(transaccion);
    }

    @Override
    public Transaccion actualizar(Integer id, Transaccion transaccion) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        transaccionRepository.deleteById(id);
    }
}
