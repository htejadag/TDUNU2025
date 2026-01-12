package com.example.MsGeneral.Service.Imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsGeneral.Repository.NotificacionRepository;

@Service
public class NotificacionServiceImp {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    

}
