package tdunu.MsTitulacion.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import tdunu.MsTitulacion.model.request.TesisBorradorRequest;
import tdunu.MsTitulacion.model.response.TesisBorradorResponse;
import tdunu.MsTitulacion.repository.TesisBorradorRepository;
import tdunu.MsTitulacion.service.TesisBorradorService;

public class TesisBorradorServiceImp implements TesisBorradorService{
    
    @Autowired
    private TesisBorradorRepository tesisBorradorRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<TesisBorradorResponse> listar(){
        return null;
    }

    @Override
    public TesisBorradorResponse obtenerPorId(int id){
        return null;
    }

    @Override
    public TesisBorradorResponse guardar(TesisBorradorRequest request){
        return null;
    }

    @Override
    public TesisBorradorResponse actualizar(int id,TesisBorradorRequest request){
        return null;
    } 

    @Override
    public void eliminar(int id){
        
    } //solo pruebas, no implementar

}
