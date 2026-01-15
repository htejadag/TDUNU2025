package com.pago.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pago.model.entity.DevolucionModel;
import com.pago.model.request.DevolucionRequest;
import com.pago.model.response.DevolucionResponse;
import com.pago.repository.DevolucionRepository;
import com.pago.service.DevolucionService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DevolucionServiceImp implements DevolucionService {

    private final DevolucionRepository devolucionRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<DevolucionResponse> listar() {
        return devolucionRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, DevolucionResponse.class))
                .toList();
    }

    @Override
    public DevolucionResponse obtenerPorId(Integer id) {
        return devolucionRepository.findById(id)
                .map(model -> modelMapper.map(model, DevolucionResponse.class))
                .orElse(null);
    }

    @Override
    public DevolucionResponse guardar(DevolucionRequest request) {
        DevolucionModel model = modelMapper.map(request, DevolucionModel.class);
        DevolucionModel saved = devolucionRepository.save(model);
        return modelMapper.map(saved, DevolucionResponse.class);
    }

    @Override
    public DevolucionResponse editar(Integer id, DevolucionRequest request) {
        DevolucionModel existente = devolucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe devolucion con ID: " + id));

        modelMapper.map(request, existente);
        DevolucionModel actualizado = devolucionRepository.save(existente);
        return modelMapper.map(actualizado, DevolucionResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        devolucionRepository.deleteById(id);
    }
}
