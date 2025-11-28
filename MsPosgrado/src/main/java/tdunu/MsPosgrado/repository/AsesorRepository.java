package tdunu.MsPosgrado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsPosgrado.model.AsesorModel;

@Repository
public interface AsesorRepository extends JpaRepository<AsesorModel, Integer>{

}