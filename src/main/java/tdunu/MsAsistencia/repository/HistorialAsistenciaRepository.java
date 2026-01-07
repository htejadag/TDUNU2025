package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.HistorialAsistenciaModel;
import java.util.List;

@Repository
public interface HistorialAsistenciaRepository extends JpaRepository<HistorialAsistenciaModel, Integer> {

    List<HistorialAsistenciaModel> findByDetalleAsistenciaId(Integer detalleAsistenciaId);
}
