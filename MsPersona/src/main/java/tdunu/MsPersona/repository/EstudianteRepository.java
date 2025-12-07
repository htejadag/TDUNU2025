package tdunu.MsPersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsPersona.model.entity.EstudianteModel;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteModel, Integer> {
    
    boolean existsByEstCodigo(String estCodigo);
    boolean existsByEstDni(String estDni);
}