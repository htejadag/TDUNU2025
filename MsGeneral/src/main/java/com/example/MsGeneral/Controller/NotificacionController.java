package com.example.MsGeneral.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsGeneral.Model.Response.NotificacionResponse;
import com.example.MsGeneral.Service.NotificacionService;
import com.example.MsGeneral.util.ApiRoutes;
import com.example.MsGeneral.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.BASEGENERAL + ApiRoutes.Notificacion.BASE)
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping(value = ApiRoutes.Notificacion.LISTAR)
    public ResponseBase<List<NotificacionResponse>> listar() {
        List<NotificacionResponse> lista = notificacionService.listar();
        return ResponseBase.ok(lista);
    }

}
