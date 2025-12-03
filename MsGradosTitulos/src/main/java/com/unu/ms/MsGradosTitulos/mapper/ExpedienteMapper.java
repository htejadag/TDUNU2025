package com.unu.ms.MsGradosTitulos.mapper;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.unu.ms.MsGradosTitulos.dto.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.dto.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.model.ExpedienteModel;

@Component
public class ExpedienteMapper {
// 1. Convertir de Entidad a Response
    public ExpedienteResponse toResponse(ExpedienteModel model) {
        ExpedienteResponse response = new ExpedienteResponse();
        
        // Mapeo manual de Model a Response
        response.setIdExpediente(model.getIdExpediente());
        response.setIdEstado(model.getIdEstado());
        response.setIdPersona(model.getIdPersona());
        response.setIdUsuarioCreo(model.getIdUsuarioCreo());
        response.setCodigoExpediente(model.getCodigoExpediente());
        response.setDescripcion(model.getDescripcion());
        response.setFechaApertura(model.getFechaApertura());
        response.setFechaCierre(model.getFechaCierre());
        
        return response;
    }

    // 2. Convertir de Request a Entidad
    public ExpedienteModel toEntity(ExpedienteRequest request) {
        ExpedienteModel model = new ExpedienteModel();
        
        // Mapeo manual de Request a Model
        // idExpediente se deja en 0 o se omite, se autogenera
        model.setIdEstado(request.getIdEstado());
        model.setIdPersona(request.getIdPersona());
        model.setIdUsuarioCreo(request.getIdUsuarioCreo());
        model.setCodigoExpediente(request.getCodigoExpediente());
        model.setDescripcion(request.getDescripcion());
        model.setFechaApertura(request.getFechaApertura());
        model.setFechaCierre(request.getFechaCierre());

        return model;
    }

    // 3. Actualizar una Entidad existente con Request (para el PUT)
    public void actualizarEntity(ExpedienteModel entity, ExpedienteRequest request) {
        // Mapeo manual de Request a Entidad existente
        entity.setIdEstado(request.getIdEstado());
        entity.setIdPersona(request.getIdPersona());
        entity.setIdUsuarioCreo(request.getIdUsuarioCreo());
        entity.setCodigoExpediente(request.getCodigoExpediente());
        entity.setDescripcion(request.getDescripcion());
        entity.setFechaApertura(request.getFechaApertura());
        entity.setFechaCierre(request.getFechaCierre());
        // Se puede dejar de actualizar fechaCreacion aquí.
    }

}
