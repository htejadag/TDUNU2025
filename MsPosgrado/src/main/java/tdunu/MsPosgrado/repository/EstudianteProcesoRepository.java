package tdunu.MsPosgrado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu.MsPosgrado.model.EstudianteProceso;; 

@Repository
public interface EstudianteProcesoRepository extends JpaRepository<EstudianteProceso, Long> {
    
    
}