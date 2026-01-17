package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.entity.MatriculaModel;
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;


public interface MatriculaService {

  List<MatriculaResponse> listarMatricula();

  MatriculaResponse obtenerPorIdMatricula(Integer id);

  MatriculaResponse guardarMatricula(MatriculaRequest producto);

  void eliminarMatricula(Integer id);

    public MatriculaModel findById (Integer id);
    public MatriculaModel update(MatriculaModel matriculaModel);
}