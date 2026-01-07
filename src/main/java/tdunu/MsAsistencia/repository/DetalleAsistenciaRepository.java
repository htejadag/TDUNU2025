package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.DetalleAsistenciaModel;
import java.util.List;

@Repository
public interface DetalleAsistenciaRepository extends JpaRepository<DetalleAsistenciaModel, Integer> {

    List<DetalleAsistenciaModel> findByAsistenciaId(Integer asistenciaId);
    
    List<DetalleAsistenciaModel> findByTipoEntidadAndEntidadId(String tipoEntidad, Integer entidadId);
    
    List<DetalleAsistenciaModel> findByAsistenciaIdAndEstado(Integer asistenciaId, String estado);
}
