package main.java.unu.MsGestionDocumental.MsExpediente.service;

import unu.MsGestionDocumental.MsExpediente.model.entity.ExpedienteEntity;
import unu.MsGestionDocumental.MsExpediente.repository.ExpedienteRepository;
import unu.MsGestionDocumental.MsExpediente.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; 
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor 
@Slf4j 
public class ExpedienteService {

    private final ExpedienteRepository expedienteRepository;

    public ExpedienteEntity crearExpediente(ExpedienteEntity entity, Integer userId) {
        entity.setCreadoPor(userId); 
        entity.setActivo(true);
         ExpedienteEntity savedEntity = expedienteRepository.save(entity);
        log.info("Expediente creado exitosamente con ID: {}", savedEntity.getId());        
        return expedienteRepository.save(entity);
    }
    
    public List<ExpedienteEntity> lista() {
        return expedienteRepository.findAll();
    }
    
    public ExpedienteEntity obtenerPorId(Integer id) {
        return expedienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expediente", id));
    }
    
    public ExpedienteEntity guardar(Long id, ExpedienteEntity newEntityData, Long userId) {
        ExpedienteEntity entity = obtenerPorId(id);

        entity.setCorrelativo(newEntityData.getCorrelativo());
        entity.setAsunto(newEntityData.getAsunto());
        entity.setUsuarioId(newEntityData.getUsuarioId());
        entity.setFechaIngreso(newEntityData.getFechaIngreso());
        entity.setEditadoPor(userId);
        
        return expedienteRepository.save(entity);
    }

    
    public void eliminar(Long id, Long userId) {
        ExpedienteEntity entity = expedienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expediente no encontrado", id));

        log.warn("Eliminación Lógica", id);
        
        entity.setActivo(false);
        entity.setEditadoPor(userId);
        
        expedienteRepository.save(entity);
    }
}