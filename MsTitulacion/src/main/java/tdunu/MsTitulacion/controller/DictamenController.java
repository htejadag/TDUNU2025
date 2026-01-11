package tdunu.MsTitulacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdunu.MsTitulacion.model.request.DictamenRequest;
import tdunu.MsTitulacion.model.response.DictamenResponse;
import tdunu.MsTitulacion.service.DictamenService;
import tdunu.MsTitulacion.util.ApiRoutes;
import tdunu.MsTitulacion.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.API_PATH_GENERAL + ApiRoutes.Dictamen.BASE)
public class DictamenController {

    @Autowired
    private DictamenService dictamenService;
    

    public ResponseBase<List<DictamenResponse>> list(){
        return null;
    }

    public ResponseBase<List<DictamenResponse>> getById(int id){
        return null;
    }

    public ResponseBase<List<DictamenResponse>> listByResultCat(String cat){
        return null;
    }

    public ResponseBase<List<DictamenResponse>> save(DictamenRequest request){
        return null;
    }

    public ResponseBase<List<DictamenResponse>> update(DictamenRequest request){
        return null;
    }

    //eliminar

}
