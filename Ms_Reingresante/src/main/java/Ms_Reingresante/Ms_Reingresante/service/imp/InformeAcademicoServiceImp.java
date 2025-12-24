package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.InformeAcademicoModel;
import Ms_Reingresante.Ms_Reingresante.model.entity.resolucionModel;

// Importaciones de Modelos y Repositorios

import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.request.resolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;
import Ms_Reingresante.Ms_Reingresante.repository.InformeAcademicoRepository;
import Ms_Reingresante.Ms_Reingresante.repository.ResolucionRepository;
import Ms_Reingresante.Ms_Reingresante.service.InformeAcademicoService;


@Service
public class InformeAcademicoServiceImp implements InformeAcademicoService {

    @Autowired
    private InformeAcademicoRepository InformeAcademicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<InformeAcademicoResponse> listarInformeAcademico() {
        return InformeAcademicoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, InformeAcademicoResponse.class))
            .toList();
    }


  
      @Override
  public InformeAcademicoResponse obtenerPorIdInformeAcademico(Integer id) {
    return InformeAcademicoRepository.findById(id)
        .map(model -> modelMapper.map(model, InformeAcademicoResponse.class))
        .orElse(null);
  }

 

 @Override
 public InformeAcademicoResponse guardarInformeAcademico(InformeAcademicoRequest request) {

    InformeAcademicoModel model =
            modelMapper.map(request, InformeAcademicoModel.class);

    // 🔥 CLAVE
    model.setIdInforme(null);

    return modelMapper.map(
            InformeAcademicoRepository.save(model),
            InformeAcademicoResponse.class
    );
      }



   @Override
  public void eliminarInformeAcademico(Integer id) {
    InformeAcademicoRepository.deleteById(id);
  }



}
