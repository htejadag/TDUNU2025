package com.unu.TDUNU2025.Msbiblioteca.service.impl;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Multa;
import com.unu.TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import com.unu.TDUNU2025.Msbiblioteca.repository.MultaRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.MultaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MultaServiceImpl implements MultaService {

    private final MultaRepository repo;
    private final ModelMapper modelMapper;

    // ✅ LISTAR MULTAS
    @Override
    public List<MultaResponse> listar() {
        return repo.findAll()
                .stream()
                .map(multa -> modelMapper.map(multa, MultaResponse.class))
                .toList();
    }

    // ✅ OBTENER MULTA POR ID (Integer, NO Long)
    @Override
    public MultaResponse obtener(Integer id) {
        Multa multa = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada"));

        return modelMapper.map(multa, MultaResponse.class);
    }

    // ✅ REGISTRAR MULTA (SIN diasRetraso del request)
    @Override
    public MultaResponse registrar(MultaRequest request) {

        Multa entity = modelMapper.map(request, Multa.class);

        // ✅ Fecha automática
        entity.setFechaGeneracion(LocalDate.now());

        // ✅ Si no pagó aún
        entity.setFechaPago(null);

        // ✅ Días de retraso inicial = 0
        entity.setDiasRetraso(0);

        Multa saved = repo.save(entity);

        return modelMapper.map(saved, MultaResponse.class);
    }

    // ✅ ACTUALIZAR MULTA
    @Override
    public MultaResponse actualizar(Integer id, MultaRequest request) {

        Multa multa = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada"));

        modelMapper.map(request, multa);

        Multa updated = repo.save(multa);

        return modelMapper.map(updated, MultaResponse.class);
    }

    // ✅ ELIMINAR MULTA
    @Override
    public void eliminar(Integer id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("La multa no existe");
        }

        repo.deleteById(id);
    }
}
