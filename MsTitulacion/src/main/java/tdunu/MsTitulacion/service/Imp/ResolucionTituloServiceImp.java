package tdunu.MsTitulacion.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import tdunu.MsTitulacion.model.request.ResolucionTituloRequest;
import tdunu.MsTitulacion.model.response.ResolucionTituloResponse;
import tdunu.MsTitulacion.repository.ResolucionTituloRepository;
import tdunu.MsTitulacion.service.ResolucionTituloService;

public class ResolucionTituloServiceImp implements ResolucionTituloService{
    
    @Autowired
    private ResolucionTituloRepository dictamenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResolucionTituloResponse> listar(){
        return null;
    }
    @Override
    public ResolucionTituloResponse guardar(ResolucionTituloRequest request){
        return null;
    }

    @Override
    public ResolucionTituloResponse actualizar(int id, ResolucionTituloRequest request){
        return null;
    } //por seaca

    @Override
    public boolean eliminar(int id){
        return true;
    }

}
