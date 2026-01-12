package tdunu.MsTitulacion.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import tdunu.MsTitulacion.model.entity.ResolucionTitulo;
import tdunu.MsTitulacion.model.request.ResolucionTituloRequest;
import tdunu.MsTitulacion.model.response.ResolucionTituloResponse;
import tdunu.MsTitulacion.repository.ResolucionTituloRepository;
import tdunu.MsTitulacion.service.ResolucionTituloService;

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
        .toList();
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

        modelActual.setFechaEmision(request.getFechaEmision());
        modelActual.setIdResolucion(request.getIdResolucionTitulo());
        modelActual.setNumeroResolucion(request.getNumeroResolucion());
        modelActual.setRutaPdfTitulo(request.getRutaPdfTitulo());
        modelActual.setRegistradoPorSunedo(request.isRegistroPorSunedu());
        modelActual.setFechaEmision(request.getFechaEmision());
        

        ResolucionTitulo saved = resolucionTituloRepository.save(modelActual);
        ResolucionTitulo response = modelMapper.map(saved, ResolucionTituloResponse.class);
        return response;
    } //por seaca

    @Override
    public void eliminar(int id){
     
    }

}
