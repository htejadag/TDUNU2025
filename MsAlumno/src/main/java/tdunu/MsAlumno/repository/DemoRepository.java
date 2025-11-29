package tdunu.MsAlumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsAlumno.model.entity.DemoModel;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Integer> {
  
}
