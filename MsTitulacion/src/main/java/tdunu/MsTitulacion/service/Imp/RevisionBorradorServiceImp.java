package tdunu.MsTitulacion.service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsTitulacion.model.entity.RevisionBorrador;
import tdunu.MsTitulacion.model.request.RevisionBorradorRequest;
import tdunu.MsTitulacion.model.response.RevisionBorradorResponse;
import tdunu.MsTitulacion.repository.RevisionBorradorRepository;
import tdunu.MsTitulacion.service.RevisionBorradorService;

@Service
public class RevisionBorradorServiceImp implements RevisionBorradorService{
 
    @Autowired
    private RevisionBorradorRepository revisionBorradorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RevisionBorradorResponse> listar(){
        return revisionBorradorRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model, RevisionBorradorResponse.class))
        .collect(Collectors.toList());
    }
    @Override
    public RevisionBorradorResponse guardar(RevisionBorradorRequest request){
        RevisionBorrador model = modelMapper.map(request, RevisionBorrador.class);
        RevisionBorrador saved = revisionBorradorRepository.save(model);
        return modelMapper.map(saved, RevisionBorradorResponse.class);
    }
    @Override
    public RevisionBorradorResponse actualizar(int id,RevisionBorradorRequest request){
        RevisionBorrador modelActual = modelMapper.map(request, RevisionBorrador.class);
        
        modelActual.setAprobado(request.isAprobado());
        modelActual.setComentarios(request.getComentarios());
        modelActual.setFechaRevision(request.getFechaRevision());
        modelActual.setIdJurado(request.getIdJurado());
        modelActual.setIdTesisBorrador(request.getIdTesisBorrador());

        RevisionBorrador saved = revisionBorradorRepository.save(modelActual);
    
        return modelMapper.map(saved, RevisionBorradorResponse.class);
    } 

    @Override
    public void eliminar(int id){
        revisionBorradorRepository.deleteById(id);
    } //solo pruebas, no implementar

}
