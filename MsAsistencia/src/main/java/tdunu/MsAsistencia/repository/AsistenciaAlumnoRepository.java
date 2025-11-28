package tdunu.MsAsistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAsistencia.model.entity.AsistenciaAlumnoModel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaAlumnoRepository extends JpaRepository<AsistenciaAlumnoModel, Integer> {

    List<AsistenciaAlumnoModel> findByAlumnoId(Integer alumnoId);

    List<AsistenciaAlumnoModel> findByCursoId(Integer cursoId);

    List<AsistenciaAlumnoModel> findByFecha(LocalDate fecha);

    List<AsistenciaAlumnoModel> findByAlumnoIdAndFechaBetween(Integer alumnoId, LocalDate inicio, LocalDate fin);

    List<AsistenciaAlumnoModel> findByCursoIdAndFecha(Integer cursoId, LocalDate fecha);
}
