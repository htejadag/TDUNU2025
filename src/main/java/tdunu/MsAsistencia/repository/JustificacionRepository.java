package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.JustificacionModel;
import java.util.List;

@Repository
public interface JustificacionRepository extends JpaRepository<JustificacionModel, Integer> {

    List<JustificacionModel> findByDetalleAsistenciaId(Integer detalleAsistenciaId);
    
    List<JustificacionModel> findByAprobado(String aprobado);
}
