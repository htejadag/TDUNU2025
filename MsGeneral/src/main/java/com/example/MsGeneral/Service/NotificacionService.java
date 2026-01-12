package com.example.MsGeneral.Service;

import java.util.List;

import com.example.MsGeneral.Model.Request.NotificacionRequest;
import com.example.MsGeneral.Model.Response.NotificacionResponse;


public interface NotificacionService {

    List<NotificacionResponse> listar();
    
    NotificacionResponse guardar(NotificacionRequest notificacion);

}
