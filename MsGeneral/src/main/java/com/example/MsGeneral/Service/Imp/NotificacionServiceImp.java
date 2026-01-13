package com.example.MsGeneral.Service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsGeneral.Model.Response.NotificacionResponse;
import com.example.MsGeneral.Repository.NotificacionRepository;
import com.example.MsGeneral.Service.NotificacionService;

@Service
public class NotificacionServiceImp implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NotificacionResponse> listar() {
        return notificacionRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, NotificacionResponse.class))
                .toList(); 
    }

    

}
