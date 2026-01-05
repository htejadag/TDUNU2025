package com.pago.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pago.model.entity.ConceptoPagoModel;
import com.pago.repository.ConceptoPagoRepository;
import com.pago.service.ConceptoPagoService;

@Slf4j
@Service("concepto_pagoServicio")
public class ConceptoPagoServiceImp implements ConceptoPagoService {
    @Autowired
    @Qualifier("concepto_pagoRepositorio")
    private ConceptoPagoRepository concepto_pagoRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConceptoPagoModel> listarConceptoPago() {
        return concepto_pagoRepositorio.findAll().
        stream()
        .map(model -> modelMapper.map(model, ConceptoPagoModel.class))
        .toList();
    }

    @Override
    public ConceptoPagoModel obtenerConceptoPago(int id) {
        return concepto_pagoRepositorio.findById(id)
        .map(model -> modelMapper.map(model, ConceptoPagoModel.class))
        .orElse(null);
    }

    @Override
    public ConceptoPagoModel registrarConceptoPago(ConceptoPagoModel concepto) {
        ConceptoPagoModel model = modelMapper.map(concepto, ConceptoPagoModel.class);
        ConceptoPagoModel saved = concepto_pagoRepositorio.save(model);
        return modelMapper.map(saved, ConceptoPagoModel.class);
    }

    @Override
    public ConceptoPagoModel actualizarConceptoPago(ConceptoPagoModel concepto) {
        ConceptoPagoModel model = modelMapper.map(concepto, ConceptoPagoModel.class);
        ConceptoPagoModel saved = concepto_pagoRepositorio.save(model);
        return modelMapper.map(saved, ConceptoPagoModel.class);
    }

    @Override
    public void desactivarConceptoPago(int id) {
        concepto_pagoRepositorio.desactivar(id);
    }

    @Override
    public void eliminarConceptoPago(int id) {
        concepto_pagoRepositorio.eliminar(id);
    }

}
