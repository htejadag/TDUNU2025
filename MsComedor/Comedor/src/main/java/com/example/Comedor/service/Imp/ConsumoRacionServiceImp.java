package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Comedor.message.ConsumoMessagePublish;
import com.example.Comedor.model.entity.ConsumoRacionModel;
import com.example.Comedor.model.entity.MenuPlatoModel;
import com.example.Comedor.model.entity.TurnoModel;
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionRequest;
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionUpdateRequest;
import com.example.Comedor.model.request.consumoRacion.KafkaEvent;
import com.example.Comedor.model.response.ConsumoRacionResponse;
import com.example.Comedor.repository.ConsumoRacionRepository;
import com.example.Comedor.repository.MenuPlatoRepository;
import com.example.Comedor.service.ConsumoRacionService;
import com.example.Comedor.service.TurnoService;

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

    @Autowired
    private ConsumoMessagePublish consumoMessagePublish;

    @Autowired
    private TurnoService  turnoService;



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
    
    @Transactional
    @Override
    public ConsumoRacionResponse guardar(ConsumoRacionRequest consumoRacionRequest) {
      

      
        MenuPlatoModel plato = menuPlatoRepository.findById(consumoRacionRequest.getIdMenuPlato())
            .orElseThrow(() -> new RuntimeException("No existe plato con id: " + consumoRacionRequest.getIdMenuPlato()));

        ConsumoRacionModel modelRa = new ConsumoRacionModel();

      
        modelRa.setIdMenuPlato(plato);
        modelRa.setIdCuentaUsuario(consumoRacionRequest.getIdCuentaUsuario());
        modelRa.setFechaConsumo(LocalDate.now());

        modelRa.setUsuarioCreacion(consumoRacionRequest.getUsuarioCreacion());
        modelRa.setFechaCreacion(LocalDate.now());
       
        ConsumoRacionModel saved = consumoRacionRepository.save(modelRa);

        try {

            KafkaEvent event =new KafkaEvent();

            event.setIdCuentaUsuario(saved.getIdCuentaUsuario());
            event.setIdUsuarioCreacion(saved.getUsuarioCreacion());
            consumoMessagePublish.sendConsumoEvent(event);
            
        } catch (Exception e) {

            System.out.println("Error enviando evento kafka:"+e.getMessage());
            
        }

        TurnoModel  turno = plato.getTurno();

        turnoService.descontarRacion(turno.getId());

        return modelMapper.map(saved, ConsumoRacionResponse.class);


    }

    @Override
    public ConsumoRacionResponse modificar(Integer id, ConsumoRacionUpdateRequest consumoRacionRequest) {

        ConsumoRacionModel model = consumoRacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe consumo racion con id: " + id));

        MenuPlatoModel plato = menuPlatoRepository.findById(consumoRacionRequest.getIdMenuPlato())
            .orElseThrow(() -> new RuntimeException("No existe menu plato con id: " + consumoRacionRequest.getIdMenuPlato()));

        modelMapper.map(consumoRacionRequest, model);

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
