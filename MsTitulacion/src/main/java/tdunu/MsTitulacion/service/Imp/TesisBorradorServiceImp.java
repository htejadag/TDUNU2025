package tdunu.MsTitulacion.service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsTitulacion.model.entity.TesisBorrador;
import tdunu.MsTitulacion.model.request.TesisBorradorRequest;
import tdunu.MsTitulacion.model.response.TesisBorradorResponse;
import tdunu.MsTitulacion.repository.TesisBorradorRepository;
import tdunu.MsTitulacion.service.TesisBorradorService;

@Service
public class TesisBorradorServiceImp implements TesisBorradorService{
    
    @Autowired
    private TesisBorradorRepository tesisBorradorRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<TesisBorradorResponse> listar(){
        return tesisBorradorRepository.findAll()
        .stream()
        .map(model -> modelMapper.map(model,TesisBorradorResponse.class))
        .collect(Collectors.toList());
    }

    @Override
    public TesisBorradorResponse obtenerPorId(int id){
        return tesisBorradorRepository.findById(id)
        .map(model -> modelMapper.map(model, TesisBorradorResponse.class))
        .orElse(null);
    }

    @Override
    public TesisBorradorResponse guardar(TesisBorradorRequest request){
        TesisBorrador model = modelMapper.map(request, TesisBorrador.class);
        TesisBorrador saved = tesisBorradorRepository.save(model);
        return modelMapper.map(saved, TesisBorradorResponse.class);
    }

    @Override
    public TesisBorradorResponse actualizar(int id,TesisBorradorRequest request){
        TesisBorrador modelActual = modelMapper.map(request, TesisBorrador.class);

        modelActual.setEstadoBorrador(request.getEstadoBorrador());
        modelActual.setFechaSubida(request.getFechaSubida());
        modelActual.setIdProyecto(request.getIdProyecto());
        modelActual.setRutaBorrador(request.getRutaBorrador());
        modelActual.setRutaConstanciaCoti(request.getRutaBorrador());

        TesisBorrador saved = tesisBorradorRepository.save(modelActual);
        return modelMapper.map(saved, TesisBorradorResponse.class);
    } 

    @Override
    public void eliminar(int id){
        tesisBorradorRepository.deleteById(id);
    } //solo pruebas, no implementar

}
