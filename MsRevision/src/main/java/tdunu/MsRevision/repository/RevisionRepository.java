package tdunu.MsRevision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsTitulacion.model.entity.RevisionHistorial;

import java.util.List;

@Repository
public interface RevisionRepository extends JpaRepository<RevisionHistorial, Integer> {
    
    // Listar todo el historial de un proyecto específico
    List<RevisionHistorial> findByIdProyectoOrderByFechaRevisionDesc(Integer idProyecto);

    // Listar revisiones hechas por un usuario específico (Ej: Docente revisando sus pendientes)
    List<RevisionHistorial> findByIdRevisorUsuario(Integer idRevisorUsuario);
}