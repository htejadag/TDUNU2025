package unu.MsMatriculaCepre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unu.MsMatriculaCepre.model.entity.Matricula;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    // Validar si ya existe matrícula de estudiante en grupo
    boolean existsByEstudianteIdAndGrupoId(Integer estudianteId, Integer grupoId);

    // Validar excluyendo un ID (para actualizaciones)
    boolean existsByEstudianteIdAndGrupoIdAndMatriculaIdNot(Integer estudianteId, Integer grupoId, Integer matriculaId);

    // Buscar por estudiante
    List<Matricula> findByEstudianteId(Integer estudianteId);

    // Buscar por grupo
    List<Matricula> findByGrupoId(Integer grupoId);

    // Buscar por estado
    List<Matricula> findByEstado(String estado);

    // Buscar por estudiante y estado
    List<Matricula> findByEstudianteIdAndEstado(Integer estudianteId, String estado);

    // Buscar por grupo y estado
    List<Matricula> findByGrupoIdAndEstado(Integer grupoId, String estado);

    // Buscar matrícula específica de estudiante en grupo
    Optional<Matricula> findByEstudianteIdAndGrupoId(Integer estudianteId, Integer grupoId);

    // Buscar por sistema origen
    List<Matricula> findBySistemaOrigen(String sistemaOrigen);
}
