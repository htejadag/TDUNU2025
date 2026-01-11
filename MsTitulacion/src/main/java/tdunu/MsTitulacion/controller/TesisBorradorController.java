package tdunu.MsTitulacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdunu.MsTitulacion.model.request.TesisBorradorRequest;
import tdunu.MsTitulacion.model.response.TesisBorradorResponse;
import tdunu.MsTitulacion.service.TesisBorradorService;
import tdunu.MsTitulacion.util.ApiRoutes;
import tdunu.MsTitulacion.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.API_PATH_GENERAL + ApiRoutes.TesisBorrador.BASE)
public class TesisBorradorController {
    
    @Autowired
    private TesisBorradorService tesisBorradorService;

    public ResponseBase<List<TesisBorradorResponse>> list(){
        return null;
    }

    public ResponseBase<List<TesisBorradorResponse>> getById(int id){
        return null;
    }


    public ResponseBase<List<TesisBorradorResponse>> save(TesisBorradorRequest request){
        return null;
    }

    public ResponseBase<List<TesisBorradorResponse>> update(TesisBorradorRequest request){
        return null;
    }

     //eliminar

}
