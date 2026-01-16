package unu.MsMatriculaCepre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unu.MsMatriculaCepre.model.entity.Matricula;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    // ==================== VALIDACIONES ====================

    // Validar si ya existe matrícula de estudiante en un grupo
    boolean existsByEstudianteIdAndGrupoId(Integer estudianteId, Integer grupoId);

    // Validar excluyendo una matrícula (para actualizaciones)
    boolean existsByEstudianteIdAndGrupoIdAndMatriculaIdNot(
            Integer estudianteId,
            Integer grupoId,
            Integer matriculaId
    );

    // ==================== BÚSQUEDAS ====================

    // Buscar todas las matrículas de un estudiante
    List<Matricula> findByEstudianteId(Integer estudianteId);

    // Buscar todas las matrículas de un grupo
    List<Matricula> findByGrupoId(Integer grupoId);

    // Buscar matrículas por estado
    List<Matricula> findByEstado(String estado);

    // Buscar matrículas por estudiante y estado
    List<Matricula> findByEstudianteIdAndEstado(Integer estudianteId, String estado);

    // Buscar matrículas por grupo y estado
    List<Matricula> findByGrupoIdAndEstado(Integer grupoId, String estado);

    // Buscar matrícula específica de un estudiante en un grupo
    Optional<Matricula> findByEstudianteIdAndGrupoId(Integer estudianteId, Integer grupoId);

    // Buscar matrículas por sistema de origen
    List<Matricula> findBySistemaOrigen(String sistemaOrigen);
}
