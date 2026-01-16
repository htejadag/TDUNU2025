package unu.MsMatriculaCepre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.MsMatriculaCepre.model.entity.Grupo;
import unu.MsMatriculaCepre.util.Turno;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    
    Optional<Grupo> findByCodigo(String codigo);
    
    List<Grupo> findByProcesoAcademicoAndEstado(String procesoAcademico, String estado);
    
    List<Grupo> findByEstado(String estado);
    
    List<Grupo> findByTurnoAndEstado(Turno turno, String estado);
    
    List<Grupo> findByProcesoAcademicoAndTurnoAndEstado(String procesoAcademico, Turno turno, String estado);
    
    @Query("SELECT g FROM Grupo g WHERE g.procesoAcademico = ?1 AND g.estado = 'ACTIVO' AND g.capacidadActual < g.capacidadMaxima")
    List<Grupo> findGruposDisponibles(String procesoAcademico);
    
    @Query("SELECT g FROM Grupo g WHERE g.procesoAcademico = ?1 AND g.turno = ?2 AND g.estado = 'ACTIVO' AND g.capacidadActual < g.capacidadMaxima")
    List<Grupo> findGruposDisponiblesPorTurno(String procesoAcademico, Turno turno);
    
    boolean existsByCodigo(String codigo);
    
    // ✅ AGREGAR ESTE MÉTODO si no existe
    Optional<Grupo> findById(Integer id);
}