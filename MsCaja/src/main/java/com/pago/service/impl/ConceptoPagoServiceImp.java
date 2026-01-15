package com.pago.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pago.model.entity.ConceptoPagoModel;
import com.pago.model.request.ConceptoPagoRequest;
import com.pago.model.response.ConceptoPagoResponse;
import com.pago.repository.ConceptoPagoRepository;
import com.pago.service.ConceptoPagoService;

@Slf4j
@Service
public class ConceptoPagoServiceImp implements ConceptoPagoService {

    @Autowired
    ConceptoPagoRepository conceptoPagoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConceptoPagoResponse> listar() {
        log.info("[ConceptoPago] listar - inicio");
        List<ConceptoPagoResponse> resp = conceptoPagoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, ConceptoPagoResponse.class))
                .toList();
        log.info("[ConceptoPago] listar - fin | total={}", resp.size());
        return resp;
    }

    @Override
    public ConceptoPagoResponse obtenerPorId(Integer id) {
        log.info("[ConceptoPago] obtenerPorId - id={}", id);
        ConceptoPagoResponse resp = conceptoPagoRepository.findById(id)
                .map(model -> modelMapper.map(model, ConceptoPagoResponse.class))
                .orElse(null);
        if (resp == null) {
            log.warn("[ConceptoPago] obtenerPorId - no encontrado | id={}", id);
        } else {
            log.info("[ConceptoPago] obtenerPorId - encontrado | id={}", id);
        }
        return resp;
    }

    @Override
    public ConceptoPagoResponse guardar(ConceptoPagoRequest request) {
        log.info("[ConceptoPago] guardar - inicio");
        ConceptoPagoModel model = modelMapper.map(request, ConceptoPagoModel.class);
        ConceptoPagoModel saved = conceptoPagoRepository.save(model);
        log.info("[ConceptoPago] guardar - ok | id={}", saved.getConceptopagoid());
        return modelMapper.map(saved, ConceptoPagoResponse.class);
    }

    @Override
    public ConceptoPagoResponse editar(Integer id, ConceptoPagoRequest request) {
        log.info("[ConceptoPago] editar - inicio | id={}", id);
        ConceptoPagoModel existente = conceptoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe concepto pago con ID: " + id));
        modelMapper.map(request, existente);
        ConceptoPagoModel actualizado = conceptoPagoRepository.save(existente);
        log.info("[ConceptoPago] editar - ok | id={}", id);
        return modelMapper.map(actualizado, ConceptoPagoResponse.class);
    }

    @Override
    public void desactivar(Integer id) {
        log.info("[ConceptoPago] desactivar - inicio | id={}", id);
        conceptoPagoRepository.desactivar(id);
        log.info("[ConceptoPago] desactivar - ok | id={}", id);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("[ConceptoPago] eliminar - inicio | id={}", id);
        conceptoPagoRepository.eliminar(id);
        log.info("[ConceptoPago] eliminar - ok | id={}", id);
    }
}
