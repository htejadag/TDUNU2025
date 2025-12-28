package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.ProcesoReingresoModel;
import Ms_Reingresante.Ms_Reingresante.model.request.ProcesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.ProcesoReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.ProcesoReingresoRepository;
import Ms_Reingresante.Ms_Reingresante.service.ProcesoReingresoService;



@Service
public class ProcesoReingresanteServiceImp implements ProcesoReingresoService {
 
   @Autowired
    private ProcesoReingresoRepository procesoReingresoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProcesoReingresoResponse> listarProcesoReingreso() {
        return procesoReingresoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, ProcesoReingresoResponse.class))
            .toList();
    }  



   
     @Override
  public ProcesoReingresoResponse obtenerPorIdProcesoReingreso(Integer id) {
    return procesoReingresoRepository.findById(id)
        .map(model -> modelMapper.map(model, ProcesoReingresoResponse.class))
        .orElse(null);
  }

 
@Override
public ProcesoReingresoResponse guardarProcesoReingreso(ProcesoReingresoRequest request) {

    ProcesoReingresoModel model =
            modelMapper.map(request, ProcesoReingresoModel.class);

    // 🔥 CLAVE: forzar INSERT
    model.setIdProceso(null);

    ProcesoReingresoModel saved =
            procesoReingresoRepository.save(model);

    return modelMapper.map(saved, ProcesoReingresoResponse.class);
}



@Override
  public void eliminarProcesoReingreso(Integer id) {
    procesoReingresoRepository.deleteById(id);
  }



}
