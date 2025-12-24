package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.ConsumoRacionModel;
import com.example.Comedor.model.entity.MenuPlatoModel;
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionRequest;
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionUpdateRequest;
import com.example.Comedor.model.response.ConsumoRacionResponse;
import com.example.Comedor.repository.ConsumoRacionRepository;
import com.example.Comedor.repository.MenuPlatoRepository;
import com.example.Comedor.service.ConsumoRacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumoRacionServiceImp implements ConsumoRacionService {
    @Autowired
    MenuPlatoRepository menuPlatoRepository;
    @Autowired
    ConsumoRacionRepository consumoRacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConsumoRacionResponse> listar() {
        return consumoRacionRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, ConsumoRacionResponse.class))
            .toList();
    }

    @Override
    public ConsumoRacionResponse obtenerPorId(Integer id) {
        return consumoRacionRepository.findById(id)
            .map(model -> modelMapper.map(model, ConsumoRacionResponse.class))
            .orElse(null);
    }

    @Override
    public ConsumoRacionResponse guardar(ConsumoRacionRequest consumoRacionRequest) {
      

        ConsumoRacionModel modelRa = new ConsumoRacionModel();

    
        MenuPlatoModel plato = menuPlatoRepository.findById(consumoRacionRequest.getIdMenuPlato())
            .orElseThrow(() -> new RuntimeException("No existe menu dia con id: " + consumoRacionRequest.getIdMenuPlato()));

      
        modelRa.setIdMenuPlato(plato);
        modelRa.setIdCuentaUsuario(consumoRacionRequest.getIdCuentaUsuario());
        modelRa.setFechaConsumo(LocalDate.now());

        modelRa.setUsuarioCreacion(consumoRacionRequest.getUsuarioCreacion());
        modelRa.setFechaCreacion(LocalDate.now());
       
        ConsumoRacionModel saved = consumoRacionRepository.save(modelRa);

        ConsumoRacionResponse response = modelMapper.map(saved, ConsumoRacionResponse.class);

        return response;
    }

    @Override
    public ConsumoRacionResponse modificar(Integer id, ConsumoRacionUpdateRequest consumoRacionRequest) {

        ConsumoRacionModel model = consumoRacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe consumo racion con id: " + id));

        MenuPlatoModel plato = menuPlatoRepository.findById(consumoRacionRequest.getIdMenuPlato())
            .orElseThrow(() -> new RuntimeException("No existe menu plato con id: " + consumoRacionRequest.getIdMenuPlato()));

        model.setIdMenuPlato(plato);
        model.setIdCuentaUsuario(consumoRacionRequest.getIdCuentaUsuario());
        model.setFechaConsumo(LocalDate.now());

        model.setUsuarioModificacion(consumoRacionRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        ConsumoRacionModel actualizado=consumoRacionRepository.save(model);

        return modelMapper.map(actualizado, ConsumoRacionResponse.class);
     
    }

    @Override
    public ConsumoRacionResponse eliminar(Integer id) {

        ConsumoRacionModel model = consumoRacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe consumo racion con id: " + id));

        model.setActivo(false);

        ConsumoRacionModel actualizado=consumoRacionRepository.save(model);

        return modelMapper.map(actualizado, ConsumoRacionResponse.class);


    }
    
   

    
}
