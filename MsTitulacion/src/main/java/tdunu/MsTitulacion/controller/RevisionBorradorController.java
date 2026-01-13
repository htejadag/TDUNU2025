package tdunu.MsTitulacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    @GetMapping(value = ApiRoutes.RevisionBorrador.LISTAR)
    public ResponseBase<List<RevisionBorradorResponse>> list(){
        List<RevisionBorradorResponse> myList = revisionBorradorService.listar();
        return ResponseBase.ok(myList);
    }

    @PostMapping(value = ApiRoutes.RevisionBorrador.REGISTRAR)
    public ResponseBase<RevisionBorradorResponse> save(@RequestBody RevisionBorradorRequest request){
        RevisionBorradorResponse response = revisionBorradorService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.RevisionBorrador.ACTUALIZAR)
    public ResponseBase<RevisionBorradorResponse> update(@PathVariable("id") int id, @RequestBody RevisionBorradorRequest request){
        RevisionBorradorResponse response = revisionBorradorService.actualizar(id, request);
        return ResponseBase.ok(response);
    }

     //pruebas eliminar
    @DeleteMapping(value = ApiRoutes.RevisionBorrador.ELIMINAR)
    public Boolean delete(@PathVariable("id") int id){
        revisionBorradorService.eliminar(id);
        return true;
    }

}
