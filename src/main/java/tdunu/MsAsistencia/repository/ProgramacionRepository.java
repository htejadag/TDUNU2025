package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.ProgramacionModel;
import java.util.List;

@Repository
public interface ProgramacionRepository extends JpaRepository<ProgramacionModel, Integer> {

    List<ProgramacionModel> findBySistemaOrigen(String sistemaOrigen);
    
    List<ProgramacionModel> findByTipoProgramacion(String tipoProgramacion);
    
    List<ProgramacionModel> findByTipoContextoAndContextoId(String tipoContexto, Integer contextoId);
    
    List<ProgramacionModel> findBySistemaOrigenAndEstado(String sistemaOrigen, String estado);
}
