package tdunu.MsTitulacion.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tdunu.MsTitulacion.model.request.DictamenRequest;
import tdunu.MsTitulacion.model.response.DictamenResponse;
import tdunu.MsTitulacion.repository.DictamenRepository;
import tdunu.MsTitulacion.service.DictamenService;

@Service
@Slf4j
public class DictamenServiceImp implements DictamenService{
    
    @Autowired
    private DictamenRepository dictamenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DictamenResponse> listar(){
        return null;
    }
    @Override
    public List<DictamenResponse> listarByResultadoCat(String categoria){
        return null;
    }
    public DictamenResponse obtenerPorId(int id){
        return null;
    }
    @Override
    public DictamenResponse guardar(DictamenRequest request){
        return null;
    }
    @Override
    public DictamenResponse actualizar(int id,DictamenRequest request){
        return null;
    }
    @Override
    public boolean eliminar (int id){
        return true;
    }
}
