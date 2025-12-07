package tdunu.MsPersona.service;

import java.util.List;
import tdunu.MsPersona.model.request.EstudianteRequest;
import tdunu.MsPersona.model.response.EstudianteResponse;

public interface EstudianteService {

  List<EstudianteResponse> listar();

  EstudianteResponse obtenerPorId(Integer id);

  EstudianteResponse guardar(EstudianteRequest producto);

  void eliminar(Integer id);

}