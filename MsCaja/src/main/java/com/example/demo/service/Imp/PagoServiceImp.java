package com.example.demo.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.PagoModel;
import com.example.demo.model.request.PagoRequest;
import com.example.demo.model.response.PagoResponse;
import com.example.demo.repository.PagoRepository;
import com.example.demo.service.PagoService;

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
  public void eliminar(Integer id) {
    pagoRepository.deleteById(id);
  }

  

}