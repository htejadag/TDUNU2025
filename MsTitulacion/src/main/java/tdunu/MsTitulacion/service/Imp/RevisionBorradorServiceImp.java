package tdunu.MsTitulacion.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import tdunu.MsTitulacion.model.request.RevisionBorradorRequest;
import tdunu.MsTitulacion.model.response.RevisionBorradorResponse;
import tdunu.MsTitulacion.repository.RevisionBorradorRepository;
import tdunu.MsTitulacion.service.RevisionBorradorService;

public class RevisionBorradorServiceImp implements RevisionBorradorService{
 
    @Autowired
    private RevisionBorradorRepository dictamenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RevisionBorradorResponse> listar(){
        return null;
    }
    @Override
    public RevisionBorradorResponse guardar(RevisionBorradorRequest request){
        return null;
    }
    @Override
    public RevisionBorradorResponse actualizar(int id,RevisionBorradorRequest request){
        return null;
    } 

    @Override
    public boolean eliminar(int id){
        return true;
    } //solo pruebas, no implementar

}
