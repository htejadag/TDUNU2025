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
    
    @GetMapping(value = ApiRoutes.Dictamen.LISTAR)
    public ResponseBase<List<DictamenResponse>> list(){
        return ResponseBase.ok(dictamenService.listar());
    }

    @GetMapping(value = ApiRoutes.Dictamen.OBTENER)
    public ResponseBase<DictamenResponse> getById(@PathVariable("id") int id){
        return ResponseBase.ok(dictamenService.obtenerPorId(id));
    }

    @GetMapping(value = ApiRoutes.Dictamen.LISTARCATRESULTADO)
    public ResponseBase<List<DictamenResponse>> listByResultCat(@PathVariable("categoria") String cat){
        return ResponseBase.ok(dictamenService.listarByResultadoCat(cat));
    }

    @PostMapping(value = ApiRoutes.Dictamen.REGISTRAR)
    public ResponseBase<DictamenResponse> save(@RequestBody DictamenRequest request){
        return ResponseBase.ok(dictamenService.guardar(request));
    }

    @PutMapping(value = ApiRoutes.Dictamen.ACTUALIZAR)
    public ResponseBase<DictamenResponse> update(@PathVariable("id") int id, @RequestBody DictamenRequest request){
        return ResponseBase.ok(dictamenService.actualizar(id, request));
    }

    //pruebas eliminar
    @DeleteMapping(value = ApiRoutes.Dictamen.ELIMINAR)
    public Boolean delete(@PathVariable("id") int id){
        dictamenService.eliminar(id);
        return true;
    }
}
