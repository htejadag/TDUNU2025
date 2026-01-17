package tdunu.MsTitulacion.service.Imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsTitulacion.helper.NotificacionHelper;
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

    @Autowired
    private NotificacionHelper notificacionHelper;

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
        model.setIdRevisionBorrador(0);
        RevisionBorrador saved = revisionBorradorRepository.save(model);

        notificacionHelper.notificarResultadoRevision(saved);
        return modelMapper.map(saved, RevisionBorradorResponse.class);
    }

    //esto es extra
    @Override
    public RevisionBorradorResponse actualizar(int id,RevisionBorradorRequest request){
        RevisionBorrador modelActual = revisionBorradorRepository.findById(id).orElse(null);
        
        modelActual.setAprobado(request.isAprobado());
        modelActual.setComentarios(request.getComentarios());
        modelActual.setFechaRevision(LocalDateTime.now());
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
