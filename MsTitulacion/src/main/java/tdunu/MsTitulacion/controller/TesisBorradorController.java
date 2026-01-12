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

    @GetMapping(value = ApiRoutes.TesisBorrador.LISTAR)
    public ResponseBase<List<TesisBorradorResponse>> list(){
        List<TesisBorradorResponse> myList = tesisBorradorService.listar();
        return ResponseBase.ok(myList);
    }

    @GetMapping(value = ApiRoutes.TesisBorrador.OBTENER)
    public ResponseBase<TesisBorradorResponse> getById(@PathVariable("id") int id){
        TesisBorradorResponse response = tesisBorradorService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.TesisBorrador.REGISTRAR)
    public ResponseBase<TesisBorradorResponse> save(@RequestBody TesisBorradorRequest request){
        TesisBorradorResponse response = tesisBorradorService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.TesisBorrador.ACTUALIZAR)
    public ResponseBase<TesisBorradorResponse> update(@PathVariable("id") int id, @RequestBody TesisBorradorRequest request){
        TesisBorradorResponse response = tesisBorradorService.actualizar(id,request);
        return ResponseBase.ok(response);
    }

     //pruebas eliminar
    @DeleteMapping(value =  ApiRoutes.TesisBorrador.ELIMINAR)
    public Boolean delete(@PathVariable("id") int id){
        Boolean deleted = tesisBorradorService.eliminar(id);
        return deleted;
    }

}
