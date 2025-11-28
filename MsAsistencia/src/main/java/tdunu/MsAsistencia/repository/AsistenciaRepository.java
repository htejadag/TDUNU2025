package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.AsistenciaModel;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<AsistenciaModel, Integer> {

    List<AsistenciaModel> findBySistemaOrigen(String sistemaOrigen);

    List<AsistenciaModel> findByTipoEntidadAndEntidadId(String tipoEntidad, Integer entidadId);

    List<AsistenciaModel> findByTipoEventoAndEventoId(String tipoEvento, Integer eventoId);

    List<AsistenciaModel> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    List<AsistenciaModel> findBySistemaOrigenAndTipoEntidad(String sistemaOrigen, String tipoEntidad);
}
