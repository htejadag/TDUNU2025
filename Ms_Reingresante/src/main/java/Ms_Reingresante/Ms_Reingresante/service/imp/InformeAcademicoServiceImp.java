package Ms_Reingresante.Ms_Reingresante.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ms_Reingresante.Ms_Reingresante.model.entity.InformeAcademicoModel;

// Importaciones de Modelos y Repositorios

import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.InformeAcademicoRepository;
import Ms_Reingresante.Ms_Reingresante.service.InformeAcademicoService;


@Service
public class InformeAcademicoServiceImp implements InformeAcademicoService {

    private InformeAcademicoRepository InformeAcademicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<InformeAcademicoResponse> listar() {
        return InformeAcademicoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, InformeAcademicoResponse.class))
            .toList();
    }
   
  
      @Override
  public InformeAcademicoResponse obtenerPorId(Integer id) {
    return InformeAcademicoRepository.findById(id)
        .map(model -> modelMapper.map(model, InformeAcademicoResponse.class))
        .orElse(null);
  }

 
   @Override
  public InformeAcademicoResponse guardar(InformeAcademicoRequest request) {
    // 1. Request -> Model
    InformeAcademicoModel model = modelMapper.map(request,InformeAcademicoModel.class);

    // 2. Guardar en BD
    InformeAcademicoModel saved = InformeAcademicoRepository.save(model);

    // 3. Model -> Response
   InformeAcademicoResponse response = modelMapper.map(saved, InformeAcademicoResponse.class);

    return response;
  }


   @Override
  public void eliminar(Integer id) {
    InformeAcademicoRepository.deleteById(id);
  }



}
