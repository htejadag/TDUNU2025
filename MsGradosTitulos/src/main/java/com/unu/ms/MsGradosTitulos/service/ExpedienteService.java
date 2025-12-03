package com.unu.ms.MsGradosTitulos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unu.ms.MsGradosTitulos.dto.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.dto.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.mapper.ExpedienteMapper;
import com.unu.ms.MsGradosTitulos.model.ExpedienteModel;
import com.unu.ms.MsGradosTitulos.repository.ExpedienteRepository;

@Service
public class ExpedienteService implements IExpedienteService {

    private static final Logger log = LoggerFactory.getLogger(ExpedienteService.class);

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ExpedienteMapper expedienteMapper;

    @Override
    public ExpedienteResponse agregar(ExpedienteRequest request) {
        log.info("Guardando expediente...");

        // 1. Convertir Request -> Entidad
        ExpedienteModel entidad = expedienteMapper.toEntity(request);

        // 2. Guardar en BD
        ExpedienteModel guardado = expedienteRepository.save(entidad);

        // 3. Convertir Entidad -> DTO y devolver
        return expedienteMapper.toResponse(guardado);
    }

    @Override
    public ExpedienteResponse actualizarExpediente(Integer id, ExpedienteRequest request) {
        log.info("Actualizando expediente ID: {}", id);

        ExpedienteModel entidadDB = expedienteRepository.findById(id).orElse(null);
        if (entidadDB == null) {
            return null;
        }

        // Actualizar campos usando el mapper
        expedienteMapper.actualizarEntity(entidadDB, request);

        // Guardar
        ExpedienteModel actualizado = expedienteRepository.save(entidadDB);
        return expedienteMapper.toResponse(actualizado);
    }

    @Override
    public ExpedienteResponse obtenerExpedientePorId(Integer id) {
        ExpedienteModel entidad = expedienteRepository.findById(id).orElse(null);
        if (entidad == null) return null;
        return expedienteMapper.toResponse(entidad);
    }

    @Override
    public List<ExpedienteResponse> obtenerExpedientes() {
        List<ExpedienteModel> lista = expedienteRepository.findAll();
        return lista.stream()
                .map(entidad -> expedienteMapper.toResponse(entidad))
                .collect(Collectors.toList());
    }

    @Override
    public boolean eliminarExpediente(Integer id) {
        if (expedienteRepository.existsById(id)) {
            expedienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
