package tdunu.MsPersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsPersona.model.entity.DocenteModel;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteModel, Integer> {
    
    boolean existsByDocCodigo(String docCodigo);
    boolean existsByDocDni(String docDni);
    boolean existsByDocEmail(String docEmail);
}