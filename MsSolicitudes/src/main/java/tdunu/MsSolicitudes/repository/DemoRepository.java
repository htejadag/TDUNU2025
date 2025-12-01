package tdunu.MsSolicitudes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsSolicitudes.model.entity.SolicitudesModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface DemoRepository extends JpaRepository<SolicitudesModel, Integer> {
    List<SolicitudesModel> findByIdEstudiante(Integer idEstudiante);
    
    List<SolicitudesModel> findByProcEstado(String estado);
    
    List<SolicitudesModel> findByProcFaseActual(String fase);
    
    Optional<SolicitudesModel> findByProcCodigo(String codigo);
    
    List<SolicitudesModel> findByIdEstudianteAndProcEstado(Integer idEstudiante, String estado);

}
