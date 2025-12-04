package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.procesoReingresoModel;
import Ms_Reingresante.Ms_Reingresante.model.request.procesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.procesoReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.procesoReingresoRepository;
import Ms_Reingresante.Ms_Reingresante.service.ProcesoReingresoService;



@Service
public class ProcesoReingresanteServiceImp implements ProcesoReingresoService {
 
    private procesoReingresoRepository ProcesoReingresanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<procesoReingresoResponse> listar() {
        return ProcesoReingresanteRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, procesoReingresoResponse.class))
            .toList();
    }
   
     @Override
  public procesoReingresoResponse obtenerPorId(Integer id) {
    return ProcesoReingresanteRepository.findById(id)
        .map(model -> modelMapper.map(model, procesoReingresoResponse.class))
        .orElse(null);
  }

 
   @Override
  public procesoReingresoResponse guardar(procesoReingresoRequest request) {
    // 1. Request -> Model
    procesoReingresoModel model = modelMapper.map(request,procesoReingresoModel.class);

    // 2. Guardar en BD
    procesoReingresoModel saved = ProcesoReingresanteRepository.save(model);

    // 3. Model -> Response
   procesoReingresoResponse response = modelMapper.map(saved, procesoReingresoResponse.class);

    return response;
  }

@Override
  public void eliminar(Integer id) {
    ProcesoReingresanteRepository.deleteById(id);
  }



}
