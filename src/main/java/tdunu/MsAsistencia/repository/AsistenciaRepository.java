package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.AsistenciaModel;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<AsistenciaModel, Integer> {

    List<AsistenciaModel> findByProgramacionId(Integer programacionId);
    
    List<AsistenciaModel> findByFecha(LocalDate fecha);
    
    List<AsistenciaModel> findByProgramacionIdAndFecha(Integer programacionId, LocalDate fecha);
    
    List<AsistenciaModel> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
