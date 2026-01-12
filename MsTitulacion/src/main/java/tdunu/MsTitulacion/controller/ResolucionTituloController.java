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

    @GetMapping(value = ApiRoutes.ResolucionTitulo.LISTAR)
    public ResponseBase<List<ResolucionTituloResponse>> list(){
        List<ResolucionTituloResponse> myList = resolucionTituloService.listar();
        return ResponseBase.ok(myList);
    }

    @PostMapping(value = ApiRoutes.ResolucionTitulo.REGISTRAR)
    public ResponseBase<ResolucionTituloResponse> save(@RequestBody ResolucionTituloRequest request){
        ResolucionTituloResponse response = resolucionTituloService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.ResolucionTitulo.ACTUALIZAR)
    public ResponseBase<ResolucionTituloResponse> update(@PathVariable("id") int id,@RequestBody ResolucionTituloRequest request){
        ResolucionTituloResponse response = resolucionTituloService.actualizar(id, request);
        return ResponseBase.ok(response);
    }

     //pruebas eliminar
    @DeleteMapping(value = ApiRoutes.ResolucionTitulo.ELIMINAR)
    public Boolean delete(@PathVariable("id") int id){
        Boolean deleted = resolucionTituloService.eliminar(id);
        return deleted;
    }

}
