package tdunu.MsTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsTemplate.model.DemoModel;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Integer> {
  
}
