package tdunu.MsTitulacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdunu.MsTitulacion.model.request.RevisionBorradorRequest;
import tdunu.MsTitulacion.model.response.RevisionBorradorResponse;
import tdunu.MsTitulacion.service.RevisionBorradorService;
import tdunu.MsTitulacion.util.ApiRoutes;
import tdunu.MsTitulacion.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.API_PATH_GENERAL + ApiRoutes.RevisionBorrador.BASE)
public class RevisionBorradorController {
    
    @Autowired
    private RevisionBorradorService revisionBorradorService;


    public ResponseBase<List<RevisionBorradorResponse>> list(){
        return null;
    }

    public ResponseBase<List<RevisionBorradorResponse>> save(RevisionBorradorRequest request){
        return null;
    }

    public ResponseBase<List<RevisionBorradorResponse>> update(RevisionBorradorRequest request){
        return null;
    }

     //eliminar
    
}
