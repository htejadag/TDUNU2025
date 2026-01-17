package com.pago.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pago.model.entity.DetallePagoModel;
import com.pago.model.request.DetallePagoRequest;
import com.pago.model.response.DetallePagoResponse;
import com.pago.repository.DetallePagoRepository;
import com.pago.service.DetallePagoService;

@Slf4j
@Service
public class DetallePagoServiceImp implements DetallePagoService {
  @Autowired
  DetallePagoRepository detallePagoRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<DetallePagoResponse> listar() {
    return detallePagoRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model, DetallePagoResponse.class))
        .toList();
  }

  @Override
  public DetallePagoResponse obtenerPorId(Integer id) {
    return detallePagoRepository.findById(id)
        .map(model -> modelMapper.map(model, DetallePagoResponse.class))
        .orElse(null);
  }

  @Override
  public DetallePagoResponse guardar(DetallePagoRequest request) {
    // 1. Request -> Model
    DetallePagoModel model = modelMapper.map(request, DetallePagoModel.class);

    // 2. Guardar en BD
    DetallePagoModel saved = detallePagoRepository.save(model);

    // 3. Model -> Response
    DetallePagoResponse response = modelMapper.map(saved, DetallePagoResponse.class);

    return response;
  }

  @Override
  public DetallePagoResponse editar(Integer id, DetallePagoRequest request) {
    DetallePagoModel existente = detallePagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe detalle de detalle pago con ID: " + id));

    // Mapear solo los campos no nulos del request al modelo existente
    modelMapper.map(request, existente);

    DetallePagoModel actualizado = detallePagoRepository.save(existente);

    return modelMapper.map(actualizado, DetallePagoResponse.class);
  }


  @Override
  public void eliminar(Integer id) {
    detallePagoRepository.deleteById(id);
  }




  

}