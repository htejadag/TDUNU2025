package tdunu.MsTitulacion.service.Imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsTitulacion.model.entity.ResolucionTitulo;
import tdunu.MsTitulacion.model.request.ResolucionTituloRequest;
import tdunu.MsTitulacion.model.response.ResolucionTituloResponse;
import tdunu.MsTitulacion.repository.ResolucionTituloRepository;
import tdunu.MsTitulacion.service.ResolucionTituloService;

@Service
public class ResolucionTituloServiceImp implements ResolucionTituloService{
    
    @Autowired
    private ResolucionTituloRepository resolucionTituloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResolucionTituloResponse> listar(){
        return resolucionTituloRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model, ResolucionTituloResponse.class))
        .collect(Collectors.toList());
    }
    @Override
    public ResolucionTituloResponse guardar(ResolucionTituloRequest request){
        ResolucionTitulo model = modelMapper.map(request, ResolucionTitulo.class);
        ResolucionTitulo saved = resolucionTituloRepository.save(model);
        return modelMapper.map(saved, ResolucionTituloResponse.class);
    }

    @Override
    public ResolucionTituloResponse actualizar(int id, ResolucionTituloRequest request){
        ResolucionTitulo modelActual = resolucionTituloRepository.findById(id).orElse(null);
        
        modelActual.setIdResolucion(request.getIdResolucionTitulo());
        if (request.getNumeroResolucion() != null) {modelActual.setNumeroResolucion(request.getNumeroResolucion());}
        if (request.getRutaPdfTitulo() != null) {modelActual.setRutaPdfTitulo(request.getRutaPdfTitulo());}
        modelActual.setRegistradoPorSunedo(request.isRegistroPorSunedu());

        ResolucionTitulo saved = resolucionTituloRepository.save(modelActual);

        return modelMapper.map(saved, ResolucionTituloResponse.class);
    } //por seaca

    @Override
    public void eliminar(int id){
        resolucionTituloRepository.deleteById(id);
    }

}
