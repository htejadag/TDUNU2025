package com.pago.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pago.model.entity.NotaCreditoModel;
import com.pago.model.request.NotaCreditoRequest;
import com.pago.model.response.NotaCreditoResponse;
import com.pago.repository.NotaCreditoRepository;
import com.pago.service.NotaCreditoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaCreditoServiceImp implements NotaCreditoService {

    private final NotaCreditoRepository notaCreditoRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<NotaCreditoResponse> listar() {
        return notaCreditoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, NotaCreditoResponse.class))
                .toList();
    }

    @Override
    public NotaCreditoResponse obtenerPorId(Integer id) {
        return notaCreditoRepository.findById(id)
                .map(model -> modelMapper.map(model, NotaCreditoResponse.class))
                .orElse(null);
    }

    @Override
    public NotaCreditoResponse guardar(NotaCreditoRequest request) {
        NotaCreditoModel model = modelMapper.map(request, NotaCreditoModel.class);
        NotaCreditoModel saved = notaCreditoRepository.save(model);
        return modelMapper.map(saved, NotaCreditoResponse.class);
    }

    @Override
    public NotaCreditoResponse editar(Integer id, NotaCreditoRequest request) {
        NotaCreditoModel existente = notaCreditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe nota credito con ID: " + id));

        modelMapper.map(request, existente);
        NotaCreditoModel actualizado = notaCreditoRepository.save(existente);
        return modelMapper.map(actualizado, NotaCreditoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        notaCreditoRepository.eliminar(id);
    }
}
