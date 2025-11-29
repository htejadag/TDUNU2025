package tdunu.MsSolicitudes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsSolicitudes.model.entity.SolicitudesModel;

@Repository
public interface DemoRepository extends JpaRepository<SolicitudesModel, Integer> {
  
}
