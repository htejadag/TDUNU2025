package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.procesoReingresoModel;
import Ms_Reingresante.Ms_Reingresante.model.request.procesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.procesoReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;
import Ms_Reingresante.Ms_Reingresante.repository.ResolucionRepository;
import Ms_Reingresante.Ms_Reingresante.repository.procesoReingresoRepository;
import Ms_Reingresante.Ms_Reingresante.service.ProcesoReingresoService;



@Service
public class ProcesoReingresanteServiceImp implements ProcesoReingresoService {
 
   @Autowired
    private procesoReingresoRepository procesoReingresoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<procesoReingresoResponse> listarProcesoReingreso() {
        return procesoReingresoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, procesoReingresoResponse.class))
            .toList();
    }  



   
     @Override
  public procesoReingresoResponse obtenerPorIdProcesoReingreso(Integer id) {
    return procesoReingresoRepository.findById(id)
        .map(model -> modelMapper.map(model, procesoReingresoResponse.class))
        .orElse(null);
  }

 
@Override
public procesoReingresoResponse guardarProcesoReingreso(procesoReingresoRequest request) {

    procesoReingresoModel model =
            modelMapper.map(request, procesoReingresoModel.class);

    // 🔥 CLAVE: forzar INSERT
    model.setIdProceso(null);

    procesoReingresoModel saved =
            procesoReingresoRepository.save(model);

    return modelMapper.map(saved, procesoReingresoResponse.class);
}



@Override
  public void eliminarProcesoReingreso(Integer id) {
    procesoReingresoRepository.deleteById(id);
  }



}
