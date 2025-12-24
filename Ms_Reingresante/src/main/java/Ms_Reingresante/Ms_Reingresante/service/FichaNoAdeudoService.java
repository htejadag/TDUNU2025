package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.FichaNoAdeudoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;




public interface FichaNoAdeudoService {

  List<FichaNoAdeudoResponse> listarFichaNoAdeudo();

  FichaNoAdeudoResponse obtenerPorIdFichaNoAdeudo(Integer id);

  FichaNoAdeudoResponse guardarFichaNoAdeudo(FichaNoAdeudoRequest producto);

  void eliminarFichaNoAdeudo(Integer id);



  
}