package unu.MsMatriculaCepre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.MsMatriculaCepre.model.entity.MatriculaGrupo;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaGrupoRepository extends JpaRepository<MatriculaGrupo, Long> {
    
    List<MatriculaGrupo> findByMatriculaMatriculaId(Integer matriculaId);
    
    List<MatriculaGrupo> findByGrupoId(Long grupoId);
    
    Optional<MatriculaGrupo> findByMatriculaMatriculaIdAndEstado(Integer matriculaId, String estado);
    
    @Query("SELECT mg FROM MatriculaGrupo mg WHERE mg.grupo.id = ?1 AND mg.estado = 'ACTIVO'")
    List<MatriculaGrupo> findActiveByGrupoId(Long grupoId);
    
    boolean existsByMatriculaMatriculaIdAndEstado(Integer matriculaId, String estado);
    
    @Query("SELECT COUNT(mg) FROM MatriculaGrupo mg WHERE mg.grupo.id = ?1 AND mg.estado = 'ACTIVO'")
    Long countActiveByGrupoId(Long grupoId);

    boolean existsByMatriculaMatriculaIdAndEstado(Long matriculaId, String estado);
}