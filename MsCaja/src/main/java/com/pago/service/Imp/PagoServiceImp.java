package com.pago.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pago.model.entity.PagoModel;
import com.pago.model.request.PagoRequest;
import com.pago.model.response.PagoResponse;
import com.pago.repository.PagoRepository;
import com.pago.service.PagoService;

@Slf4j
@Service
public class PagoServiceImp implements PagoService {

  @Autowired
  PagoRepository pagoRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<PagoResponse> listar() {
    return pagoRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model, PagoResponse.class))
        .toList();
  }

  @Override
  public PagoResponse obtenerPorId(Integer id) {
    return pagoRepository.findById(id)
        .map(model -> modelMapper.map(model, PagoResponse.class))
        .orElse(null);
  }

  @Override
  public PagoResponse guardar(PagoRequest request) {
    // 1. Request -> Model
    PagoModel model = modelMapper.map(request, PagoModel.class);

    // 2. Guardar en BD
    PagoModel saved = pagoRepository.save(model);

    // 3. Model -> Response
    PagoResponse response = modelMapper.map(saved, PagoResponse.class);

    return response;
  }

  @Override
  public PagoResponse editar(Integer id, PagoRequest request) {
    PagoModel existente = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe pago con ID: " + id));

    // Mapear solo los campos no nulos del request al modelo existente
    modelMapper.map(request, existente);

    PagoModel actualizado = pagoRepository.save(existente);

    return modelMapper.map(actualizado, PagoResponse.class);
  }


  @Override
  public void eliminar(Integer id) {
    pagoRepository.deleteById(id);
  }

  

}