package tdunu.MsPosgrado.service;


import java.util.List;
import java.util.Optional;

import tdunu.MsPosgrado.model.EstudianteProceso;

public interface EstudianteProcesoService {
    
    List<EstudianteProceso> listarTodos();
    
    EstudianteProceso guardar(EstudianteProceso proceso);
    
    Optional<EstudianteProceso> buscarPorId(Long id);
    
    void eliminar(Long id);
}