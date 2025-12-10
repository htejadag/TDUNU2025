package com.example.Comedor.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.ConsumoRacionModel;
import com.example.Comedor.model.entity.MenuDiaModel;
// import com.example.Comedor.model.entity.MenuSemanaModel;
// import com.example.Comedor.model.entity.TurnoModel;
import com.example.Comedor.model.request.ConsumoRacionRequest;
import com.example.Comedor.model.response.ConsumoRacionResponse;
// import com.example.Comedor.model.response.MenuDiaResponse;
import com.example.Comedor.repository.ConsumoRacionRepository;
import com.example.Comedor.repository.MenuDiaRepository;
import com.example.Comedor.service.ConsumoRacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumoRacionServiceImp implements ConsumoRacionService {
    @Autowired
    MenuDiaRepository menuDiaRepository;
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
        // MenuDiaModel model = new MenuDiaModel();

        ConsumoRacionModel modelRa = new ConsumoRacionModel();

        //
        MenuDiaModel dia = menuDiaRepository.findById(consumoRacionRequest.getIdMenuDia())
            .orElseThrow(() -> new RuntimeException("No existe menu dia con id: " + consumoRacionRequest.getIdMenuDia()));

        // Asignar relaciones correctamente
        modelRa.setIdMenuDia(dia);
        modelRa.setIdCuentaUsuario(consumoRacionRequest.getIdCuentaUsuario());
        modelRa.setFechaConsumo(consumoRacionRequest.getFechaConsumo());

        modelRa.setUsuarioCreacion(consumoRacionRequest.getUsuarioCreacion());
        modelRa.setFechaCreacion(consumoRacionRequest.getFechaCreacion().toString());
        modelRa.setUsuarioModificacion(consumoRacionRequest.getUsuarioModificacion());
        modelRa.setFechaModificacion(consumoRacionRequest.getFechaModificacion().toString());
        // Guardar
        ConsumoRacionModel saved = consumoRacionRepository.save(modelRa);

        return modelMapper.map(saved, ConsumoRacionResponse.class);
    }

    @Override
    public ConsumoRacionResponse modificar(Integer id, ConsumoRacionRequest consumoRacionRequest) {
        ConsumoRacionModel existingModel = consumoRacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe consumo racion con id: " + id));

        MenuDiaModel dia = menuDiaRepository.findById(consumoRacionRequest.getIdMenuDia())
            .orElseThrow(() -> new RuntimeException("No existe menu dia con id: " + consumoRacionRequest.getIdMenuDia()));

        // Actualizar las propiedades del modelo existente
        existingModel.setIdMenuDia(dia);
        // Aquí puedes actualizar otras propiedades según sea necesario

        // Guardar los cambios
        ConsumoRacionModel updatedModel = consumoRacionRepository.save(existingModel);

        return modelMapper.map(updatedModel, ConsumoRacionResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        consumoRacionRepository.deleteById(id);
    }
    
   

    
}
