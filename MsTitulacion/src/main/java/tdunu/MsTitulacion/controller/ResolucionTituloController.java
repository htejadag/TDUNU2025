package tdunu.MsTitulacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdunu.MsTitulacion.model.request.ResolucionTituloRequest;
import tdunu.MsTitulacion.model.response.ResolucionTituloResponse;
import tdunu.MsTitulacion.service.ResolucionTituloService;
import tdunu.MsTitulacion.util.ApiRoutes;
import tdunu.MsTitulacion.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.API_PATH_GENERAL + ApiRoutes.ResolucionTitulo.BASE)
public class ResolucionTituloController {
    
    @Autowired
    private ResolucionTituloService resolucionTituloService;

    public ResponseBase<List<ResolucionTituloResponse>> list(){
        return null;
    }

    public ResponseBase<List<ResolucionTituloResponse>> save(ResolucionTituloRequest request){
        return null;
    }

    public ResponseBase<List<ResolucionTituloResponse>> update(ResolucionTituloRequest request){
        return null;
    }

     //eliminar

}
