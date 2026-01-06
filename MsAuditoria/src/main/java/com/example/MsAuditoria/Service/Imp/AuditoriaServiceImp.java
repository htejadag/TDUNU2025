package com.example.MsAuditoria.Service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsAuditoria.Model.Entity.Auditoria;
import com.example.MsAuditoria.Model.Request.AuditoriaRequest;
import com.example.MsAuditoria.Model.Response.AuditoriaResponse;
import com.example.MsAuditoria.Repository.AuditoriaRepository;
import com.example.MsAuditoria.Service.AuditoriaService;

@Service
public class AuditoriaServiceImp implements AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuditoriaResponse guardar(AuditoriaRequest request) {
        Auditoria model = modelMapper.map(request, Auditoria.class);

        Auditoria saved = auditoriaRepository.save(model);

        AuditoriaResponse response = modelMapper.map(saved, AuditoriaResponse.class);
        
        return response;
    }

    @Override
    public List<AuditoriaResponse> listar() {
        return auditoriaRepository.findAll()
        .stream()
        .map(model->modelMapper.map(model, AuditoriaResponse.class))
        .toList();
    }

    @Override
    public List<AuditoriaResponse> listarPorEntidad(String entidad, String idEntidad) {
        return auditoriaRepository.findByEntidadAndIdEntidad(entidad, idEntidad)
        .stream()
        .map(model->modelMapper.map(model, AuditoriaResponse.class))
        .toList();
    }

    @Override
    public List<AuditoriaResponse> listarPorMicroservicio(String microservicio) {
        return  auditoriaRepository.findByMicroservicio(microservicio)
        .stream()
        .map(model->modelMapper.map(model, AuditoriaResponse.class))
        .toList();
    }
    
}
