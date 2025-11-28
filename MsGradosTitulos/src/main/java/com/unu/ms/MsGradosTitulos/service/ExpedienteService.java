package com.unu.ms.MsGradosTitulos.service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unu.ms.MsGradosTitulos.dto.ExpedienteDTO;
import com.unu.ms.MsGradosTitulos.model.ExpedienteModel;
import com.unu.ms.MsGradosTitulos.repository.ExpedienteRepository;

@Service
public class ExpedienteService {
    
    @Autowired
    private ExpedienteRepository expedienteRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public List<ExpedienteDTO> obtenerTodos() {
        List<ExpedienteModel> expedientes = expedienteRepository.findAll();
        return expedientes.stream()
                .map(expediente -> modelMapper.map(expediente, ExpedienteDTO.class))
                .toList();
    }
    
 
    public Optional<ExpedienteDTO> obtenerPorId(Integer id) {
        Optional<ExpedienteModel> expediente = expedienteRepository.findById(id);
        return expediente.map(exp -> modelMapper.map(exp, ExpedienteDTO.class));
    }
    

    public ExpedienteDTO crear(ExpedienteDTO expedienteDTO) {
        ExpedienteModel expediente = modelMapper.map(expedienteDTO, ExpedienteModel.class);
        ExpedienteModel expedienteGuardado = expedienteRepository.save(expediente);
        return modelMapper.map(expedienteGuardado, ExpedienteDTO.class);
    }
    
  
    public Optional<ExpedienteDTO> actualizar(Integer id, ExpedienteDTO expedienteDTO) {
        Optional<ExpedienteModel> expedienteExistente = expedienteRepository.findById(id);
        
        if (expedienteExistente.isPresent()) {
            ExpedienteModel expediente = expedienteExistente.get();
            
            // Mapear DTO a model (solo actualiza campos no nulos)
            if (expedienteDTO.getCodigoExpediente() != null && !expedienteDTO.getCodigoExpediente().isEmpty()) {
                expediente.setCodigoExpediente(expedienteDTO.getCodigoExpediente());
            }
            if (expedienteDTO.getIdPersona() > 0) {
                expediente.setIdPersona(expedienteDTO.getIdPersona());
            }
            if (expedienteDTO.getIdEstado() > 0) {
                expediente.setIdEstado(expedienteDTO.getIdEstado());
            }
            if (expedienteDTO.getDescripcion() != null && !expedienteDTO.getDescripcion().isEmpty()) {
                expediente.setDescripcion(expedienteDTO.getDescripcion());
            }
            if (expedienteDTO.getFechaApertura() != null) {
                expediente.setFechaApertura(expedienteDTO.getFechaApertura());
            }
            if (expedienteDTO.getFechaCierre() != null) {
                expediente.setFechaCierre(expedienteDTO.getFechaCierre());
            }
            
            ExpedienteModel expedienteActualizado = expedienteRepository.save(expediente);
            return Optional.of(modelMapper.map(expedienteActualizado, ExpedienteDTO.class));
        }
        
        return Optional.empty();
    }
    
    public boolean eliminar(Integer id) {
        if (expedienteRepository.existsById(id)) {
            expedienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
